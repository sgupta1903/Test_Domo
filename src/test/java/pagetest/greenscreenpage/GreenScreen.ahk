#NoEnv  ; Recommended for performance and compatibility with future AutoHotkey releases.
; #Warn  ; Enable warnings to assist with detecting common errors.
SendMode Input  ; Recommended for new scripts due to its superior speed and reliability.
SetWorkingDir %A_ScriptDir%  ; Ensures a consistent starting directory.


; ********************AHK Script Starts From Here**************************
InputArray:=[]                                                  ; Array declaration to save  DM.csv input file data
DataTypeArray:=[]                                               ; Array declaration for CSV  file 1 Test Case ID's to store
TestCaseKeywordArray:=[]                                        ; Array declaration for CSV  file 2 data to store
ActionsArray:=[]                                                ; Array declaration for CSV  file 3 data to store
ParameterArray:=[]                                              ; Array declaration to save input parameters
; ***********************Global Variables *********************************
global path
global testCaseId
global tagInfo
global targetIniPath
global error := false
global errorMessage
global newPID
global iniPath
global defaultSleep
global count
global winWidth
global winHeight
path= %A_ScriptDir%                                             ; Script Directory Path

  ;******************* Setting datamigration.ini path *****************************
  iniPath=%1%
  ;************* DM.csv Is Source Input Data File which will have Club number And Member Number List *************

    ;###### Setting window width and height ######
    winWidth := readIni("APP_PARAMS","winWidth")
    winHeight := readIni("APP_PARAMS","winHeight")

    ;###### Setting default sleep and count ######
        defaultSleep := readIni("APP_PARAMS","defaultSleep")
        count := readIni("APP_PARAMS","count")

  ;###### Making DM.csv Path ######
  inputArrayPath := StrReplace(path,readIni("APP_PARAMS","BASE_PATH"),readIni("APP_PARAMS","DM_FILE_PATH"))

  ;######Storing  "DM.csv"  file data into inputArray ######
   InputArray := buildArray(inputArrayPath)

  ;###### Making TestCaseAndKeywords.csv Path ######
  testCaseArrayPath := StrReplace(path,readIni("APP_PARAMS","BASE_PATH"),readIni("APP_PARAMS","TC_KEYWORDS_FILE_PATH"))

  ;######Storing  "TestCaseAndKeywords.csv"  file data into testCaseArray ######
  TestCaseKeywordArray := buildArray(testCaseArrayPath)

  ;###### Making KeywordActions.csv Path ######
  keywordsArrayPath:= StrReplace(path,readIni("APP_PARAMS","BASE_PATH"),readIni("APP_PARAMS","ACTIONS_FILE_PATH"))

  ;######Storing  "KeywordActions.csv"  file data into actionInputArray #######
  ActionsArray := buildArray(keywordsArrayPath)

  ;###### Making DataType.csv Path ######
  dataTypeArrayPath:= StrReplace(path,readIni("APP_PARAMS","BASE_PATH"),readIni("APP_PARAMS","TEST_CASES_FILE_PATH"))

  ;######Storing  "DataType.csv"  file data into dataTypeArray #######
  DataTypeArray := buildArray(dataTypeArrayPath)

  ;###### Making greenscreen.ini Path ######
  targetIniPath := StrReplace(path,readIni("APP_PARAMS","BASE_PATH"),readIni("APP_PARAMS","GREEN_SCREEN_INI_PATH"))

  ;###### Making AHK Script execution completion indicator file Path ######
  exeCompleteIniPath := StrReplace(path,readIni("APP_PARAMS","BASE_PATH"),readIni("APP_PARAMS","NOTIFI_FILE_PATH"))

  ;###### Deleting execution completion indicator file before execution (If File Exist) ######
  FileDelete, %exeCompleteIniPath%

  ;###### Cleaning greenscreen.ini before execution ######
  file := FileOpen(targetIniPath, "w")
  file.close()

      ;###### 1st Loop : To Iterate InputArray ######
  Loop, % InputArray.MaxIndex()
{
            inputArrayRowIndex:=A_Index
            ;MsgBox, % InputArray[inputArrayRowIndex]
            ;Fetch the all the columns from DM
            colDMArray:=StrSplit(InputArray[inputArrayRowIndex], ",")

            ; Fetching All Parameters
            index:=2
            while(index<=colDMArray.MaxIndex())
            {
              ParameterArray[index-1]:=colDMArray[index]
              index++
            }
           ;Fetching the location/member column(the colindex should be 1 always as the member/location columns are found at index 1 in DM.csv)
           ;MsgBox, % colDMArray[1]
           ;Pass the location/member's cell as needle to Data Type Array to fetch the row index
           dataTypeArrayRowIndex:=getRowIndex(DataTypeArray,colDMArray[1])
           ;MsgBox, %  dataTypeArrayRowIndex

           ;To fetch all the test cases  (location,A30TP-18556...) from the DataType file for location/member
           TestCaseArray:=StrSplit(DataTypeArray[dataTypeArrayRowIndex], ",")
           ;Iterate through each test cases skipping the first column as its location/member
           ;MsgBox, % DataTypeArray[1]
           testCaseArrayColIndex:=2
           ;MsgBox, % TestCaseArray.MaxIndex()
           ;MsgBox, % testCaseArrayColIndex

           login(readIni("APP_PARAMS","USER NAME"),readIni("APP_PARAMS","PASSWORD"))

           ;###### 2nd Loop : To Iterate Test Case Array ######

    while (testCaseArrayColIndex<=TestCaseArray.MaxIndex())
    {
            testCaseId:=% TestCaseArray[testCaseArrayColIndex]
            tagInfo:=buildTag(ParameterArray)

            ; Writing Tag In INI When Test Case Starts
            FileAppend, [%tagInfo%] `n,%targetIniPath%

            testCaseKeywordArrayRowIndex:=getRowIndex(TestCaseKeywordArray, TestCaseArray[testCaseArrayColIndex])
            ;MsgBox, % testCaseKeywordArrayRowIndex
            ;MsgBox, % testCaseId
            ;MsgBox, % tagInfo
            testCaseArrayColIndex++
            ;Iterate through Test Cases Keywords
            testCaseKeywordArrayColIndex:=2
            KeywordArray:=StrSplit(TestCaseKeywordArray[testCaseKeywordArrayRowIndex], ",")
            ;MsgBox, % TestCaseKeywordArray[testCaseKeywordArrayRowIndex]
            ;MsgBox, % testCaseKeywordArrayRowIndex

            ;###### 3rd Loop : To Iterate Keywords Array ######
            while (error=false && testCaseKeywordArrayColIndex<=KeywordArray.MaxIndex())
	       {
	               ;MsgBox, "Enter Actions Loop"
                   ;fetch the Actions Row Index as per the Keyword
                   ;MsgBox, % KeywordArray[testCaseKeywordArrayColIndex]
                   ActionsArrayRowIndex:=getRowIndex(ActionsArray, LTrim(RTrim(KeywordArray[testCaseKeywordArrayColIndex])))
                   ;MsgBox, % ActionsArrayRowIndex

                   ;contains row by row values in ActionsArray
                   ActionCols:=StrSplit(ActionsArray[ActionsArrayRowIndex], ",")
                   ;Number of ACTIONS AND INPUTS IN A ROW(CURRENTLY there are 2 different values of Actions and Inputs in a Row)
                   ; will change as per requirement
                   numberofActionsInputsInRow:=2
                   counter:=2
               loop, %numberofActionsInputsInRow%
             {
            	   Action:= % ActionCols[counter]
                   counter++
            	   Input:= % ActionCols[counter]
                   counter++
                   ;MsgBox, % Action
                   ;MsgBox, % Input
                   Switch Action
                  {
                        Case "Login Action":
                             ;MsgBox Running Test Case : %testCaseId%
                             login(readIni("APP_PARAMS","USER NAME"),readIni("APP_PARAMS","PASSWORD"))
                        Case "Send":
                              send(input,ParameterArray)
                              Sleep, 500
                        Case "VerifyStatus":
                              verifyStatus(input)
                              Sleep, 500
                        Case "Keypress Until Keyword":
                              keyPressUntil(input)
                        Case "Copy And Append Content":
                              copyAndAppend(input,tagInfo)
                        Case "Copy By Line And Append Content":
                              copyMultiPages(input,tagInfo,ParameterArray)
                        Case "Copy Multi Line By Page Count And Append Content":
                              copyMultiPagesByCount(input,tagInfo,ParameterArray)
                      ; Case "Exit":
                      ;       exitBlueZoneApp()
                        Default:
                      ;       MsgBox No Test Found
                  }
            }
          testCaseKeywordArrayColIndex++
        }                                    ; 3rd Loop Ends here
           if(error=true)
           {
             onFailure(tagInfo)              ; writing error message in INI after test fails
             exitAndRelogin(tagInfo)         ; exit and relogin after test fails
           } else
           {
             FileAppend, Status= Pass `n,%targetIniPath%
           }
    }                                        ; 2nd Loop Ends here
   exitBlueZoneApp()
}                                            ; 1st Loop Ends here
FileAppend, ,%exeCompleteIniPath%            ; Creating indicator file here for java verification program
ExitApp                                      ; Green Screen Script Execution complete here
return


; ----------------------------
; |     Verify Page Load     |
; ----------------------------
   verifyStatus(input)
 {
       try
         {
            updateInput := StrSplit(StrReplace(input,"|",","), ",")
            StringReplace,x1,%  updateInput[1],`", , All
            y1:=%  updateInput[2]
            x2:=%  updateInput[3]
            y2:=%  updateInput[4]
            StringReplace,content,%  updateInput[5],`", , All
            ;MsgBox Arguments Value is %x1% ,%y1%,%x2% ,%y2%, %content%
            loopCount = 0;
            Clipboard := ""
            if(x1 = "" || y1 ="" || x2 = "" || y2 ="" || content ="")
            {
              throw Exception("Missing or Incomplete Input Parameters", -1)
            }
            ;MsgBox After Check Arguments Value is %x1%,%y1%,%x2% ,%y2%, %content%
            While (Clipboard != content && loopCount < count)
            {
              copy(x1 ,y1,x2 ,y2)
              Sleep, 500
              ++loopCount
            }
            Sleep, 500
            if(Clipboard = content)
            {
              ;  MsgBox Level Completed And Value is %Clipboard%
              return "Success"
            }
            else If (loopCount >= count)
            {
              ; MsgBox "TimeoutError"
              error:= true
              errorMessage:= "Failed To Access Next Screen"
              return "Failure"
            }
        } catch exception
            {
            errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
            error:= true
            }
 }

; ----------------------------
; |     Copy Operation       |
; ----------------------------

   copy(x1 ,y1,x2 ,y2)
 {
    try
       {
          if(x1 = "" || y1 ="" || x2 = "" || y2 ="")
          {
            throw Exception("Missing or Incomplete Input Parameters For Copy Action", -1)
          }
            MouseMove, %x1%, %y1%
            Click down
            MouseMove, %x2%, %y2%
            Send, ^c
            Click up
         ;   Sleep, 100
         ;   Send {ctrl}
         ;   return
       } catch exception
          {
           errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
           error:= true
          }
 }

; ----------------------------
; | Copy And Append Content  |
; ----------------------------
  copyAndAppend(input,tagInfo)
 {
     try
     {
        modifiedInput:= StrSplit(input,"$")
        value:=""
        equal==
        nl=`n
        flag:= false
        loop % modifiedInput.MaxIndex()
        {
            updateInput := StrSplit(StrReplace(modifiedInput[A_Index],"|",","), ",")
            StringReplace,x1,%  updateInput[1],`", , All
            y1:=%  updateInput[2]
            x2:=%  updateInput[3]
            y2:=%  updateInput[4]
            StringReplace,field,%  updateInput[5],`", , All
            loopCount = 0;
            Clipboard := ""
            ;MsgBox Arguments Value is %x1% ,%y1%,%x2% ,%y2%, %field%
            if(x1 = "" || y1 ="" || x2 = "" || y2 ="")
            {
             throw Exception("Missing or Incomplete Input Parameters To Copy An Append Content", -1)
            }
            ;MsgBox After Check Arguments Value is %x1%,%y1%,%x2% ,%y2%
            While (Clipboard = "" && loopCount < count)
            {
               copy(x1 ,y1,x2 ,y2)
               Sleep, 500
               ++loopCount
            }

            if(LTrim(RTrim(Clipboard)) != "" )
            {
              flag:= true
            }
            if(field !="")
            {
              ;***************** Field Specific Key and Value Creation*********************
              value := value . field equal Clipboard nl
            }
            else
            {
                data:=copyMultiContent(updateInput)
                         ; Loop To Parse data Array
                           loop %  data.MaxIndex()
                            {
                              content:=% data[A_Index]
                              if(content!= ""){
                              value := value . "Row" loopCount "_" A_Index equal content nl
                              }

                            }
            }
        }
            if (flag=false)
            {
              value:= "Data" equal "Not Found" . nl
            }
              FileAppend, %value% ,%targetIniPath%
            if(value != "")
            {
              return "Success"
            }
            else If (loopCount >= count)
                 {
                   error:= true
                   errorMessage:= "TimeoutError ! Failed To Perform Copy And Append Content"
                   return "Failure"
                 }
     } catch exception
           {
             errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
             error:= true
           }
 }

; ---------------------------------------
; |    Build Array From CSV File        |
; ---------------------------------------

  buildArray(file)
 {
       global
       Array := []
   try
   {
       if !FileExist(file)
       {
         throw Exception("File Does not exist in directory", -1)
       }
       Loop, Read, %file% ; This loop retrieves each line from the file, one at a time.
       {
         Array.Push(A_LoopReadLine) ; Append this line to the array.
       }
      return % Array
   }catch exception
       {
         errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
         error:= true
         MsgBox, %errorMessage%
       }
 }

; ----------------------------
; |       Get Row Index      |
; ----------------------------

  getRowIndex(SearchArray,needle)
 {
   try
   {
      rowIndex:=1
      isMatch:="Not Found"
      ;MsgBox, % SearchArray.MaxIndex()
      Loop % SearchArray.MaxIndex()
      {
         loopCount:=1
         cols := StrSplit(SearchArray[A_Index], ",")
         Loop %  cols.MaxIndex()
 	     {
              value:= cols[loopCount]
              ;MsgBox, % value
                if (value==needle)
                 {
                      isMatch:="Found"
                      break
                 }
              ++loopCount
  	     }
         if(isMatch=="Found")
         {
           break
         }
       ++rowIndex
      }
     return %rowIndex%
   } catch exception
     {
        errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
        error:= true

     }
}
/*
; ----------------------------
; |         Reload           |
; ----------------------------
^+s::reloadAutoHotkeyScript()

reloadAutoHotKeyScript() {
  Reload
  Sleep 1000
  IfWinNotExist, AutoHotkey.ahk - Notepad
  MsgBox, 4,, The script could not be reloaded. Would you like to open it for editing?
  IfMsgBox, Yes, Edit
  return "Success"
}

*/


; ----------------------------
; |      Read INI File        |
; ----------------------------
  readIni(section,key)
 {
    try
    {
        if(section = "" || key ="" )
           {
                throw Exception("Section Or Key is not available In INI file", -1)
           }
               ;iniPath := StrReplace(path,"\java\pagetest\greenscreenpage","\resources\datamigration.ini")
                IniRead, value, %iniPath%, %section%, %key%
                return %value%
    } catch exception
       {
              errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
              error:= true
       }
 }

; ---------------------------
; |      Login Action      |
; --------------------------

   login(userName, password)
 {
     try
       {
            appPath:=readIni("APP_PARAMS","APPLICATION PATH")
            ;userName:=readIni("APP_PARAMS","USER NAME")
            ;password:=readIni("APP_PARAMS","PASSWORD")
            winTitle:=readIni("APP_PARAMS","WIN_TITLE")
            Run, %appPath%,,, newPID
            Process, Priority, %newPID%, High
            WinWait, %winTitle%, ,15
            IfWinNotActive, %winTitle%, , WinActivate, %winTitle%,
            WinWaitActive, %winTitle%, ,15
            Winmove,,,,,winWidth,winHeight
            ;verifyStatus(readIni("APP_PARAMS","LOGIN_SCREEN_VERIFY"))
            Sleep, 2000
               if WinActive(winTitle)
               {
                 if(userName!="" && password!="")
                    {
                         Send %userName%{TAB}%password%{ENTER}
                         Sleep, 500
                         Send, {ENTER}
                    }
                   else
                   {
                         error:= true
                   }
               }
               else
               {
                 error:= true
               }
            return
      } catch exception
               {
                  errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
                  error:= true
               }
 }


; ---------------------------
; |      Send Action        |
; ---------------------------

  send(input,ParameterArray)
   {
     try
     {
         if(input="")
         {
           throw Exception("Missing Send Input", -1)
         }
         else IfInString, input, <
         	{
         ;	MsgBox Input Is %input%
         	input:=replaceParmaters(input,ParameterArray)
         ;	MsgBox Updated Input Is %input%
         	}
           Send %input%
           Sleep, %defaultSleep%
     } catch exception
         {
           errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
           error:= true
         }
    }

; -----------------------------------------------
; |         Keypress Until Keyword Action       |
; -----------------------------------------------
   keyPressUntil(input)
 {
     try
     {
            modifiedInput:= StrSplit(input,"$")
            StringReplace,sendAction,%  modifiedInput[1],`", , All
            StringReplace,statusInput,%  modifiedInput[2],`", , All
            updateInput := StrSplit(StrReplace(statusInput,"|",","), ",")
            StringReplace,x1,%  updateInput[1],`", , All
            y1:=%  updateInput[2]
            x2:=%  updateInput[3]
            y2:=%  updateInput[4]
            StringReplace,content,%  updateInput[5],`", , All
            ;MsgBox arguments are %sendAction% , %x1% ,%y1%,%x2% ,%y2%, %content%
            Clipboard := ""
            loopCount = 0;
            if(x1 = "" || y1 ="" || x2 = "" || y2 ="" || content ="" || sendAction="")
            {
                throw Exception("Missing or Incomplete Input Parameters for Keypress Until Keyword Action", -1)
            }
        while(Clipboard != content || loopcount<count)
        {
              copy(x1 ,y1,x2 ,y2)
              Sleep, 500
           if(Clipboard != content)
           {
              Send %sendAction%
           }
           else
           {
              Break
           }
              Sleep, 500
              ++loopCount
        }
        If (loopCount >= count)
           {
              error:= true
              errorMessage:= "TimeoutError ! Failed To Perform Return Action"
              return "Failure"
           }
    } catch exception
        {
              errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
              error:= true
        }
 }


; ------------------------------------------------------------
; |      Method To Replace Parameter Value From Input         |
; -----------------------------------------------------------
   replaceParmaters(input,ParameterArray)
  {

	 Loop, % ParameterArray.MaxIndex()
        {
            replacePattern=<parameter%A_Index%>
            ;MsgBox, %replacePattern%
            input:= StrReplace(input,replacePattern ,ParameterArray[A_Index])
        }

	return %input%
  }

; -------------------------------------------------
; |      Exit Blue Zone Application Action        |
; -------------------------------------------------

    exitBlueZoneApp()
 {
   try
   {
       Process, Close, %newPID%
       return
   } catch exception
         {
                   MsgBox,% "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
         }

 }

; ------------------------------
; |       Build Tag Name       |
; ------------------------------
  buildTag(ParameterArray)
 {
     try
      {
             if(ParameterArray.MaxIndex()="")
              {
                      throw Exception("Missing Input Parameters", -1)
              }
              Loop, % ParameterArray.MaxIndex()
              tag:=tag . ParameterArray[A_Index] "_"

              tag:=tag . testCaseId
              return % tag
      } catch exception
          {
              MsgBox,% "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
          }
 }

; -----------------------------------------------
; |       Write Tag In Case Of Test Fails       |
; -----------------------------------------------
   onFailure(tagInfo)
 {
    try
    {
        ; Writing Data For Failed Club
        FileAppend, Message= %errorMessage% `n,%targetIniPath%
        FileAppend, Status=Fail `n,%targetIniPath%
    } catch exception
         {
           MsgBox,% "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
         }
 }

; ---------------------------------------
; |      Copy And Append Multi Pages    |
; --------------------------------------

   copyMultiPages(input,tagInfo,ParameterArray)
          {
              try
              {
                     value:=""
                     equal==
                     nl=`n
        			 flag:= false
        			 loopFlag:=true

        			 symbolToLook:="["
        			 searchKeyword:=""
        			 If InStr(Input, symbolToLook)
                     {
                        If InStr(Input, "<")
                        {
                           Input:=replaceParmaters(Input,ParameterArray)
                        }
                           searchKeyword:= RegexReplace(Input,".*\[(.+?)\].*","$1")
                     }
                          ; MsgBox, %searchKeyword%

                     modifiedInput:= StrSplit(input,"$")
                     StringReplace,action,%  modifiedInput[2],`", , All
                     updateInput := StrSplit(StrReplace(modifiedInput[1],"|",","), ",")

                     if (searchKeyword !="")
                     {
                       updateInput.Remove(1)
                     }

                     loopCount = 1
                     Clipboard := ""
                     while(loopFlag=true)
                 {
                     Sleep, 1000
                     data:=copyMultiContent(updateInput)

                     maxIndex:= %  data.MaxIndex()

                    ; Loop To Parse data Array
                     loop %  data.MaxIndex()
                     {
                        content:=% data[A_Index]

                        ; Checking existance of  "searchKeyword" value in each row

                        If InStr(content, searchKeyword)
                        {
                           value := value . "Row" loopCount "_" A_Index equal content nl
                        }
                        Else
                        {
                            loopFlag:=false
                            Break
                        }

                     }

                     if (loopFlag=true)
                     {
                          Send %action%
                          Sleep, %defaultSleep%
                     }

                     ++loopCount
                 }
                       value := RegExReplace(value, " +", " ")

                     if(LTrim(RTrim(value)) != "" )
                     {
        			    flag:= true
        			 }

        		   if (flag=false)
                     {
                       value:= "Data" equal "Not Found" . nl
                     }
                       FileAppend, %value% ,%targetIniPath%

              } catch exception
                    {
                      errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
                      error:= true
                    }
          }



 ; ---------------------------------------
 ; |      Copy And Append Multi Pages By Count   |
 ; --------------------------------------
copyMultiPagesByCount(input,tagInfo,ParameterArray)
       {
           try
           {
                 equal==
                 nl=`n
                  splitInput:= StrSplit(input,"&")
                 ;MsgBox, % splitInput[2]
                 value:=""
                 flag:= false

                 ;Calculating loop count:TotalPageCount/No.of Rows

                 splitPageCountAndRow := StrSplit(splitInput[2],"$")
                 ;MsgBox, % splitPageCountAndRow[1]
                 Sleep, 1000
                 ;These are coordinates of total records exist
                 totalRecordsCoordinates:= splitPageCountAndRow[1]
                 updateInput := StrSplit(StrReplace(totalRecordsCoordinates,"|",","), ",")
                 StringReplace,x1,%  updateInput[1],`", , All
                               y1:=%  updateInput[2]
                               x2:=%  updateInput[3]
                               y2:=%  updateInput[4]
                   Clipboard := ""
                   ;MsgBox Arguments Value is %x1% ,%y1%,%x2% ,%y2%, %field%
                       if(x1 = "" || y1 ="" || x2 = "" || y2 ="")
                         {
                          throw Exception("Missing or Incomplete Input Parameters To Copy An Append Content", -1)
                         }
                   ; Copying total records count using coorinates %x1%,%y1%,%x2% ,%y2%
                    copy(x1 ,y1,x2 ,y2)
                    Sleep, 500
                    copiedValue:= value . field equal Clipboard nl
                    ;MsgBox, % copiedValue
                     recordCount := % LTrim(RTrim(Clipboard))
                    ; MsgBox, % recordCount
                     rowCount := % splitPageCountAndRow[2]
                    ; MsgBox, % rowCount
                    counter:=Ceil(recordCount/rowCount)
                    ; msgbox,Result=%counter%
                    loopCount = 1

                    loop % counter
                    {
                                      equal==
                                      nl=`n
                                      modifiedInput:= StrSplit(splitInput[1],"$")
                                      ;MsgBox, % modifiedInput[1]
                                      ;MsgBox, % modifiedInput[2]
                                      updateInput := StrSplit(StrReplace(modifiedInput[1],"|",","), ",")
                                      StringReplace,action,%  modifiedInput[2],`", , All
                                      Clipboard := ""
                                      Sleep, 1000
                                      data:=copyMultiContent(updateInput)

                                      maxIndex:= %  data.MaxIndex()

                                      ; Loop To Parse data Array
                                      loop %  data.MaxIndex()-1
                                      {
                                         content:=% data[A_Index]
                                        ;MsgBox, % content
                                         value := value . "Row" loopCount "_" A_Index equal content nl
                                        ;MsgBox, % value
                                      }
                                         If (counter!=loopCount)   ; adding condition to not perform action when on first page
                                         {
                                           ; Sending action
                                            Send %action%
                                            Sleep, %defaultSleep%
                                         }
                                         ++loopCount
                    }
                     value := RegExReplace(value, " +", " ")

                     if(LTrim(RTrim(value)) = "" )
                      {
                        value:= "Data" equal "Not Found" . nl
                      }
                     FileAppend, %value% ,%targetIniPath%
           }
           catch exception
              {
                errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
                error:= true
              }
       }

; --------------------------------------
; |      Copy Mutli Content Action      |
; --------------------------------------
   copyMultiContent(input)
   {
      try
      {
           StringReplace,x1,%  input[1],`", , All
           y1:=%  input[2]
           x2:=%  input[3]
           StringReplace,y2,%  input[4],`", , All
           if(x1 = "" || y1 ="" || x2 = "" || y2 ="")
             {
                throw Exception("Missing or Incomplete Input Parameters To Perform Copy And Append Multi Page Action", -1)
             }
           copy(x1 ,y1,x2 ,y2)
           Sleep, 500
           if(LTrim(RTrim(Clipboard)) != "" )
             {
                   ; Removing Blank Line from Clipboard
                     updatedClipboard := RegExReplace(Clipboard, "`am)^[\s\R]*")
                   ; Split Clipboard data based on new line
                     Array := StrSplit(updatedClipboard,"`r`n")
              }

          return % Array
       } catch exception
              {
                          errorMessage:= "Error in : " exception.What ", at line :" exception.Line "  Error Message  :" exception.Message
                          error:= true
              }
   }

; --------------------------------------
; |      To Terminate AHK Script        |
; --------------------------------------

Esc::ExitApp

; ------------------------------------------------
; |       Hot Key To Resize Selected Window      |
; -----------------------------------------------

#r::
Winmove,,,,,winWidth,winHeight
return

; --------------------------
; |       Error Method      |
; -------------------------

exitAndRelogin(tagInfo)
{
   error:=false
   exitBlueZoneApp()
   login(readIni("APP_PARAMS","USER NAME"),readIni("APP_PARAMS","PASSWORD"))
   return
}