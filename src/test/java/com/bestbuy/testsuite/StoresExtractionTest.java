package com.bestbuy.testsuite;

import io.restassured.RestAssured;
import io.restassured.response.ValidatableResponse;
import org.junit.BeforeClass;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;

import static io.restassured.RestAssured.given;

public class StoresExtractionTest {

    static ValidatableResponse response;

    @BeforeClass
    public static void inIt() {
        RestAssured.baseURI = "http://localhost";
        RestAssured.port = 3030;
        response = given()
                .when()
                .get("/stores")
                .then().statusCode(200);
    }

    // 1. Extract the limit
    @Test
    public void extractLimit() {
        int limit = response.extract().path("limit");
        System.out.println("The value of limit is : " + limit);
    }

    //2. Extract the total
    @Test
    public void extractTheTotal(){
        int total = response.extract().path("total");
        System.out.println("The value  of total is : " + total);
    }

    //3. Extract the name of 5th store
    @Test
    public void extractTheNameOf5thStore(){
        String storeName = response.extract().path("data[4].name");
        System.out.println("The name of the 5th Store is :" + storeName);
    }

    //4. Extract the names of all the store
    @Test
    public void theNamesOfAllStore(){
        List<String> allStoreName = response.extract().path("data.name");
        System.out.println("List of stores are : " + allStoreName);
    }
    // 5. Extract the storeId of all the store
    @Test
    public void theStoreIdOfAllStore(){
        List<Integer> allStoreId = response.extract().path("data.id");
        System.out.println("List of storesId are : " + allStoreId);
    }

    // 6. Print the size of the data list
    @Test
    public void theSizeOfTheDataList(){
        List<Integer> allStoreSize = response.extract().path("data.id");
        System.out.println("The Size of all stores are : " + allStoreSize.size());
    }

    //7. Get all the value of the store where store name = St Cloud
    @Test
    public void getAllTheValueOfTheStoreNameStCloud(){
        List<HashMap<String, ?>> values = response.extract().path("data.findAll{it.name == 'St Cloud'}");
        System.out.println("Value of store name : " + values);
    }

    // 8. Get the address of the store where store name = Rochester
    @Test
    public void getAddressRochester(){
        List<HashMap<String, ?>> address = response.extract().path("data.findAll{it.name == 'Rochester'}.address");
        System.out.println("Value of store name : " + address);
    }

    //9. Get all the services of 8th store
    @Test
    public void getAllTheServicesOf8thStore(){
        List<HashMap<String,?>> services8ThStore = response.extract().path("data[7].services");
        System.out.println(services8ThStore);
    }

    //10. Get store services of the store where service name = Windows Store
    @Test
    public void getStoreServicesOfWindowsStore(){
        List<HashMap<String, ?>> servicesList = response.extract().path("data[0].services.findAll{it.name == 'Windows Store'}.storeservices");
        System.out.println(servicesList);
    }

    //11. Get all the storeId of all the store
    @Test
    public void getAllTheStoreIdOfAllTheStore(){
        List<Integer> getAllStoreId = response.extract().path("data.services.storeservices.storeId");
        System.out.println("List of storesId are : " + getAllStoreId);
    }

    //12. Get id of all the store
    @Test
    public void getIdOfAllTheStore(){
        List<Integer> getId = response.extract().path("data.id");
        System.out.println("get Id of all stores are : " + getId);
    }

    //13. Find the store names Where state = ND
    @Test
    public void findTheStateNameNd(){
        List<HashMap<String, ?>> stateName = response.extract().path("data.findAll{it.state == 'ND'}.name");
        System.out.println("Get the store name : " + stateName);
    }

    //14. Find the Total number of services for the store where store name = Rochester
    @Test
    public void theTotalNumberOfServicesStoreNameRochester(){
        List<HashMap<String, ?>> totalNumber = response.extract().path("data.findAll{it.name == 'Rochester'}.services[0]");
        System.out.println(totalNumber.size());
    }

    //15. Find the createdAt for all services whose name = “ Windows Store”
    @Test
    public void findTheCreatedAtForAllServicesWindowsStore(){
        List<?> createdAt = response.extract().path("data[0].services.findAll{it.name == 'Windows Store'}.createdAt");
        System.out.println(createdAt);
    }
    //16. Find the name of all services Where store name = “Fargo”
    @Test
    public void findTheNameOfAllServicesFargo(){
        List<String> fargoServiceName = response.extract().path("data.findAll{it.name == 'Fargo'}.services.name");
        System.out.println(fargoServiceName);
    }

    //17. Find the zip of all the store
    @Test
    public void findTheZipOfAllStore(){
        List<Integer> zip = response.extract().path("data.zip");
        System.out.println(zip);
    }

    //18. Find the zip of store name = Roseville
    @Test
    public void findTheZipOfStoreNameRoseville(){
        List<String> zip1 = response.extract().path("data.findAll{it.name == 'Roseville'}.zip");
        System.out.println(zip1);
    }

    // 19. Find the store services details of the service name = Magnolia Home Theater
    @Test
    public void findTheStoreServicesDetailsMagnoliaHomeTheater(){
       List<?> storeServices = response.extract().path("data.find{it.services}.services.findAll{it.name='Magnolia Home Theater'}.storeservices");
        System.out.println(storeServices);
    }

    //20. Find the lat of all the stores
    @Test
    public void findTheLatOfAllTheStores(){
        List<Double> lat = response.extract().path("data.lat");
        System.out.println(lat);
    }
}
