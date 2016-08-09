package com.dynamodb;

/**
 * Created by vv on 8/1/16.
 */

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import com.amazonaws.services.dynamodbv2.document.DynamoDB;
import com.amazonaws.services.dynamodbv2.document.Item;
import com.amazonaws.services.dynamodbv2.document.PutItemOutcome;
import com.amazonaws.services.dynamodbv2.document.Table;
import com.amazonaws.services.dynamodbv2.document.spec.GetItemSpec;

import java.util.*;

public class DynamoDBClient {

    private DynamoDB dynamoDBClient;

    private void createConnectionAWS() {

        AmazonDynamoDBClient dbClient = new AmazonDynamoDBClient(new ProfileCredentialsProvider());
        dbClient.withRegion(Region.getRegion(Regions.US_WEST_2));

        this.dynamoDBClient = new DynamoDB(dbClient);
    }

    public void insertItem() {
        // Modify the client so that it accesses a different region.
        //client.withRegion(Regions.US_WEST_1);
        Table table = dynamoDBClient.getTable("ProductCatalog");

        // Build a list of related items
        List<Number> relatedItems = new ArrayList<Number>();
        relatedItems.add(341);
        relatedItems.add(472);
        relatedItems.add(649);

//Build a map of product pictures
        Map<String, String> pictures = new HashMap<String, String>();
        pictures.put("FrontView", "http://example.com/products/206_front.jpg");
        pictures.put("RearView", "http://example.com/products/206_rear.jpg");
        pictures.put("SideView", "http://example.com/products/206_left_side.jpg");

//Build a map of product reviews
        Map<String, List<String>> reviews = new HashMap<String, List<String>>();

        List<String> fiveStarReviews = new ArrayList<String>();
        fiveStarReviews.add("Excellent! Can't recommend it highly enough!  Buy it!");
        fiveStarReviews.add("Do yourself a favor and buy this");
        reviews.put("FiveStar", fiveStarReviews);

        List<String> oneStarReviews = new ArrayList<String>();
        oneStarReviews.add("Terrible product!  Do not buy this.");
        reviews.put("OneStar", oneStarReviews);

        Item item = new Item()
                .withPrimaryKey("id", 206)
                .withString("Title", "20-Bicycle 206")
                .withString("Description", "206 description")
                .withString("BicycleType", "Hybrid")
                .withString("Brand", "Brand-Company C")
                .withNumber("Price", 500)
                .withStringSet("Color", new HashSet<String>(Arrays.asList("Red", "Black")))
                .withString("ProductCategory", "Bike")
                .withBoolean("InStock", true)
                .withNull("QuantityOnHand")
                .withList("RelatedItems", relatedItems)
                .withMap("Pictures", pictures)
                .withMap("Reviews", reviews);

        PutItemOutcome outcome = table.putItem(item);
        System.out.println(outcome.getPutItemResult().toString());
    }

    public void getItem() {

        Table table = dynamoDBClient.getTable("ProductCatalog");

        GetItemSpec spec = new GetItemSpec()
                .withPrimaryKey("id", 206)
                .withProjectionExpression("Id, Title, RelatedItems[0], Reviews.FiveStar")
                .withConsistentRead(true);

        Item item = table.getItem(spec);

        System.out.println(item.toJSONPretty());

    }


    public static void main(String[] args) {
        DynamoDBClient db = new DynamoDBClient();
        db.createConnectionAWS();
        db.getItem();
    }

}
