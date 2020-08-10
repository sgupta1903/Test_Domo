package pagetest.datamigrationpage;

import helper.DataBaseHandler;
import pagetest.businessapppage.AbcCommonAbstractPage;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import static helper.AppConstants.REGEX;

//Created By Monika Phoughat
//Date : 11-09-2019

public class CentralDBPage extends AbcCommonAbstractPage<CentralDBPage> {

    public String get_org_id(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Organisation Id from DB");
        Map orgMap = dataBaseHandler.executeSql("Select * from central.organization where name='" + orgName + "'");
        return orgMap.get("organization_id").toString().replaceAll(REGEX, "");
    }

    public String get_location_id(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Location Id from DB");
        Map locMap = dataBaseHandler.executeSql("Select * from central.location where organization_name='" + orgName + "'");
        return locMap.get("location_id").toString().replaceAll(REGEX, "");
    }

    public String get_client_profile_id(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Client Profile Id from DB");
       List list = dataBaseHandler.get_multiple_rows(
                "Select DISTINCT(client_profile_id) from central.client_payment_method where organization_name='" + orgName + "'AND status=201 Limit 5");
               String[] clientProfileIds = list.get(0).toString().split("=");
        return clientProfileIds[1].replace("}","");
    }

    public List<String> get_sample_item_ids(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample Item Ids from DB");
        List<String> itemsId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows(
                "Select DISTINCT(id) from central.item where organization_name='" + orgName + "' AND status=201 Limit 5");
        for (int i = 0; i < list.size(); i++) {
            String[] itemIds = list.get(i).toString().split("=");
            itemsId.add(itemIds[1].replace("}",""));
        }
        return itemsId;
    }

    public List<String> get_sample_client_configure_fee_ids(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample Client Configure Ids from DB");
        List<String> ratesId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows(
                "Select DISTINCT(client_config_fee_id) from central.client_configure_fee where org_name='" + orgName + "' AND status=201");
        for (int i = 0; i < list.size(); i++) {
            String[] ratesIds = list.get(i).toString().split("=");
            ratesId.add(ratesIds[1].replace("}",""));
        }
        return ratesId;
    }

    public List<String> get_sample_member_configure_fee_ids(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample Member Configure Ids from DB");
        List<String> feeId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows(
                "Select DISTINCT(member_config_fee_id) from central.member_configure_fee where org_name='" + orgName + "' AND status=201");
        for (int i = 0; i < list.size(); i++) {
            String[] feeIds = list.get(i).toString().split("=");
            feeId.add(feeIds[1].replace("}",""));
        }
        return feeId;
    }

    public  List<String>  get_sample_item_category_ids(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample Item Category Ids from DB");
        List<String> itemCategoryId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows(
                "Select DISTINCT(id) from central.item_category where organization_name='" + orgName + "' AND status=201 Limit 5");
        for (int i = 0; i < list.size(); i++) {
            String[] itemCategoryIds = list.get(i).toString().split("=");
            itemCategoryId.add(itemCategoryIds[1].replace("}",""));
        }
        return itemCategoryId;
    }

    public List<String> get_sample_member_ids(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample  Member Ids from DB");
        List<String> memberId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows("Select DISTINCT(member_id) from central.member_info where organization_name='" + orgName + "' AND status=201  AND primary_member_id IS NULL Limit 5");
        for (int i = 0; i < list.size(); i++) {
            String[] memberIds = list.get(i).toString().split("=");
            memberId.add(memberIds[1].replace("}",""));
        }return memberId;
    }

    public List<String> get_sample_payor_payment_method_details(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample  Payor Payment Method Ids from DB");
        List<String> payorProfileId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows("Select DISTINCT(payor_profile_id) from central.payor_payment_method where organization_name='" + orgName + "'AND status=201 Limit 5");
        for (int i = 0; i < list.size(); i++) {
            String[] payorProfileIds = list.get(i).toString().split("=");
            payorProfileId.add(payorProfileIds[1].replace("}",""));
        }return payorProfileId;
    }

    public List<String> get_sample_agreement_details(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample Agreement Ids from DB");
        List<String> agreementId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows("Select DISTINCT(agreement_id) from central.agreement where organization_name='" + orgName + "'AND status=201 Limit 5");
        for (int i = 0; i < list.size(); i++) {
            String[] agreementIds = list.get(i).toString().split("=");
            agreementId.add(agreementIds[1].replace("}",""));
        }return agreementId;
    }

    public List<String> get_sample_subscription_details(String orgName, DataBaseHandler dataBaseHandler) {
        logger.info("Fetching Sample Subscription Ids from DB");
        List<String> subscriptionId=new ArrayList<>();
        List list = dataBaseHandler.get_multiple_rows("Select DISTINCT(subscription_id) from central.subscription where organization_name='" + orgName + "'AND status=201 Limit 5");
        for (int i = 0; i < list.size(); i++) {
            String[] subscriptionIds = list.get(i).toString().split("=");
            subscriptionId.add(subscriptionIds[1].replace("}",""));
        }return subscriptionId;
    }


    public Map<String,Object> get_org_data(String orgId, DataBaseHandler dataBaseHandler) {

        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.organization where organization_id='" + orgId + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("name", getDataFromDB.get("name").toString().replaceAll(REGEX, ""));
        getData.put("email", getDataFromDB.get("email").toString().replaceAll(REGEX, ""));
        getData.put("phone", getDataFromDB.get("phn_number").toString().replaceAll(REGEX, ""));
        getData.put("timezone", getDataFromDB.get("time_zone").toString().replaceAll(REGEX, ""));
        getData.put("signInLink", getDataFromDB.get("signin_link").toString().replaceAll(REGEX, ""));
        getData.put("id", getDataFromDB.get("organization_id").toString().replaceAll(REGEX, ""));
        getData.put("address1", getDataFromDB.get("address1").toString().replaceAll(REGEX, ""));
        getData.put("address2", getDataFromDB.get("address2").toString().replaceAll(REGEX, ""));
        getData.put("city", getDataFromDB.get("city").toString().replaceAll(REGEX, ""));
        getData.put("countryId", getDataFromDB.get("country_id").toString().replaceAll(REGEX, ""));
        getData.put("stateId", getDataFromDB.get("state_id").toString().replaceAll(REGEX, ""));
        getData.put("zipCode", getDataFromDB.get("zip_code").toString().replaceAll(REGEX, ""));
        return getData;
    }
    public Map<String,Object> get_location_data(String orgId, DataBaseHandler dataBaseHandler) {
        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.location where location_id='" + orgId + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("name", getDataFromDB.get("name").toString().replaceAll(REGEX, ""));
        getData.put("email", getDataFromDB.get("email").toString().replaceAll(REGEX, ""));
        getData.put("timezone", getDataFromDB.get("time_zone").toString().replaceAll(REGEX, ""));
        getData.put("address.address1", (getDataFromDB.get("address1").toString().replaceAll(REGEX, "")));
        getData.put("address.address2", (getDataFromDB.get("address2").toString().replaceAll(REGEX, "")));
        getData.put("address.city", getDataFromDB.get("city").toString().replaceAll(REGEX, ""));
        getData.put("address.countryId", getDataFromDB.get("country_id").toString().replaceAll(REGEX, ""));
        getData.put("address.stateId", getDataFromDB.get("state_id").toString().replaceAll(REGEX, ""));
        getData.put("zipCode", getDataFromDB.get("zip_code").toString().replaceAll(REGEX, ""));
        return getData;
    }

    public Map<String,Object> get_client_profile_data(String orgId, DataBaseHandler dataBaseHandler) {
        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.client_payment_method where client_profile_id='" + orgId.replaceAll(REGEX, "") + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("routingNumber", getDataFromDB.get("routing_number").toString().replaceAll(REGEX, ""));
        getData.put("name", getDataFromDB.get("account_holder_name").toString().replaceAll(REGEX, ""));
        getData.put("lastFour", getDataFromDB.get("last_four").toString().replaceAll(REGEX, ""));
        getData.put("bankAccountType", getDataFromDB.get("type").toString().replaceAll(REGEX, ""));
        return getData;
    }
    public Map getItemData(String orgId,DataBaseHandler dataBaseHandler) {
        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.item where id='" + orgId + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("name", getDataFromDB.get("name").toString().replaceAll(REGEX, ""));
        getData.put("type", getDataFromDB.get("type").toString().replaceAll(REGEX, ""));
        getData.put("itemCategoryId", (getDataFromDB.get("item_category_id").toString().replaceAll(REGEX, "")));
        return getData;
    }

    public Map getRateData(String orgId,DataBaseHandler dataBaseHandler) {
        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.client_configure_fee where client_config_fee_id='" + orgId + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("rate_type", getDataFromDB.get("api_fee_type").toString().replaceAll(REGEX, ""));
        getData.put("start_date", getDataFromDB.get("start_date").toString().replaceAll(REGEX, ""));
        getData.put("per_transaction", getDataFromDB.get("flat_amount").toString().replaceAll(REGEX, ""));
        return getData;
    }
    public Map getFeeData(String orgId,DataBaseHandler dataBaseHandler) {
        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.member_configure_fee where member_config_fee_id='" + orgId + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("fee_type", getDataFromDB.get("api_fee_type").toString().replaceAll(REGEX, ""));
        getData.put("start_date", getDataFromDB.get("start_date").toString().replaceAll(REGEX, ""));
        getData.put("fee_amount", getDataFromDB.get("flat_amount").toString().replaceAll(REGEX, ""));
        return getData;
    }

    public Map getItemCategoryData(String orgId,DataBaseHandler dataBaseHandler ) {
        Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.item_category where id='" + orgId + "'");
        Map<String, Object> getData = new HashMap<>();
        getData.put("name", getDataFromDB.get("name").toString().replaceAll(REGEX, ""));
        getData.put("description", getDataFromDB.get("description").toString().replaceAll(REGEX, ""));
        getData.put("taxCode", getDataFromDB.get("tax_code").toString().replaceAll(REGEX, ""));
        getData.put("active", Boolean.valueOf(getDataFromDB.get("active").toString().replaceAll(REGEX, "")));
        return getData;
    }

    public List<Map<String,String>> getMemberData(List memberId,DataBaseHandler dataBaseHandler) {
        List<Map<String,String>> memberInfo=new ArrayList<>();
        for(int i=0;i<memberId.size();i++) {
            Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.member_info where member_id='" + memberId.get(i).toString() + "'");
            Map<String, String> getData = new HashMap<>();
            getData.put("firstName", getDataFromDB.get("first_name").toString().replaceAll(REGEX, ""));
            getData.put("lastName", getDataFromDB.get("last_name").toString().replaceAll(REGEX, ""));
            getData.put("birthDate", getDataFromDB.get("birth_date").toString().replaceAll(REGEX, ""));
            getData.put("email", getDataFromDB.get("email").toString().replaceAll(REGEX, ""));
            getData.put("address", getDataFromDB.get("address1").toString().replaceAll(REGEX, ""));
            getData.put("city", getDataFromDB.get("city").toString().replaceAll(REGEX, ""));
            getData.put("address.countryId", getDataFromDB.get("country_id").toString().replaceAll(REGEX, ""));
            getData.put("address.stateId", getDataFromDB.get("state_id").toString().replaceAll(REGEX, ""));
            getData.put("zipCode", getDataFromDB.get("zip_code").toString().replaceAll(REGEX, ""));
            getData.put("phoneNumber", getDataFromDB.get("home_number").toString().replaceAll(REGEX, ""));
            getData.put("invalidAddress", getDataFromDB.get("invalid_address").toString().replaceAll(REGEX, ""));
            getData.put("genderIdentity", getDataFromDB.get("gender_identity").toString().replaceAll(REGEX, ""));
            getData.put("locationId", getDataFromDB.get("location_id").toString().replaceAll(REGEX, ""));
            memberInfo.add(getData);
        }
        return memberInfo;
    }
    public List<Map<String,Object>> getPayorPaymentMethodData(List paymentMethodId,DataBaseHandler dataBaseHandler) {
        List<Map<String,Object>> payorProfileInfoDbMap=new ArrayList<>();
        for(int i=0;i<paymentMethodId.size();i++) {
            Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.payor_payment_method where payor_profile_id='" + paymentMethodId.get(i).toString() + "'");
            Map<String, Object> getData = new HashMap<>();
            getData.put("routingNumber", getDataFromDB.get("routing_number").toString().replaceAll(REGEX, ""));
            getData.put("name", getDataFromDB.get("account_holder_name").toString().replaceAll(REGEX, ""));
            getData.put("lastFour", getDataFromDB.get("last_four").toString().replaceAll(REGEX, ""));
            getData.put("type", getDataFromDB.get("pay_sub_type").toString().replaceAll(REGEX, ""));
            getData.put("expiryYear", getDataFromDB.get("expiry_year").toString().replaceAll(REGEX, ""));
            getData.put("expiryMonth", getDataFromDB.get("expiry_month").toString().replaceAll(REGEX, ""));
            getData.put("zip", getDataFromDB.get("zip_code").toString().replaceAll(REGEX, ""));
            payorProfileInfoDbMap.add(getData);
        }
        return payorProfileInfoDbMap;
    }
    public List<Map<String,Object>> getAgreementData(List agreementId,DataBaseHandler dataBaseHandler)

    {
        List<Map<String,Object>> agreementInfo=new ArrayList<>();
        for(int i=0;i<agreementId.size();i++) {
            Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.agreement where agreement_id='" + agreementId.get(i).toString() + "'");
            Map<String, Object> getData = new HashMap<>();
            getData.put("beginDate", getDataFromDB.get("start_date").toString().replaceAll(REGEX, ""));
            agreementInfo.add(getData);
        }
            return agreementInfo;
    }
    public List<Map<String,Object>> getSubscriptionData(List subscriptionId,DataBaseHandler dataBaseHandler) {
        List<Map<String,Object>> subscriptionInfo=new ArrayList<>();
        for(int i=0;i<subscriptionId.size();i++) {
            Map getDataFromDB = dataBaseHandler.executeSql("Select * from central.subscription where subscription_id='" + subscriptionId.get(i).toString() + "'");
            Map<String, Object> getData = new HashMap<>();
            getData.put("subscriptionType", getDataFromDB.get("api_sub_type").toString().replaceAll(REGEX, ""));
            getData.put("beginDate", getDataFromDB.get("start_date").toString().replaceAll(REGEX, ""));
            getData.put("amenity", getDataFromDB.get("item_name").toString().replaceAll(REGEX, ""));
            getData.put("frequency", getDataFromDB.get("api_frequency").toString().replaceAll(REGEX, ""));
            getData.put("firstDueDate", getDataFromDB.get("first_due_date").toString().replaceAll(REGEX, ""));
            getData.put("expiryDate", getDataFromDB.get("expiration_date").toString().replaceAll(REGEX, ""));
            getData.put("noOfPayments", getDataFromDB.get("number_of_payments").toString().replaceAll(REGEX, ""));
            getData.put("renewalType", getDataFromDB.get("api_renewal_sub_type").toString().replaceAll(REGEX, ""));
            getData.put("renewDate", getDataFromDB.get("renewal_renewal_date").toString().replaceAll(REGEX, ""));
            getData.put("paymentFrequency", getDataFromDB.get("renewal_frequency").toString().replaceAll(REGEX, ""));
            subscriptionInfo.add(getData);
        }
        return subscriptionInfo;
    }

   }

