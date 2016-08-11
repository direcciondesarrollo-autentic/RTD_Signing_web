package com.repositories;

import com.amazonaws.auth.profile.ProfileCredentialsProvider;
import com.amazonaws.regions.Region;
import com.amazonaws.regions.Regions;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.AmazonDynamoDBClient;
import org.socialsignin.spring.data.dynamodb.repository.config.EnableDynamoDBRepositories;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@EnableDynamoDBRepositories(basePackages = "com.repositories"/*, dynamoDBOperationsRef = "dynamoDBOperations"*/)
@Configuration
public class DynamoDBConfig {

    @Bean
    public AmazonDynamoDB amazonDynamoDB(/*AWSCredentials amazonAWSCredentials*/) {
        final AmazonDynamoDBClient client = new AmazonDynamoDBClient(new ProfileCredentialsProvider()/*amazonAWSCredentials*/);
        //client.setSignerRegionOverride(Region.getRegion(Regions.US_WEST_2).getName()/*Regions.fromName(region).getName()*/);
        client.withRegion(Region.getRegion(Regions.US_WEST_2));
        /*if (StringUtils.isNotEmpty(amazonDynamoDBEndpoint)) {
            client.setEndpoint(amazonDynamoDBEndpoint);
        } */
        return client;
    }

    /*
    @Bean
    public DynamoDBOperations dynamoDBOperations() {
        final DynamoDBTemplate dynamoDBTemplate = new DynamoDBTemplate(amazonDynamoDB(amazonAWSCredentials()));
        final DynamoDBMapperConfig.TableNameOverride tableNameOverride = DynamoDBMapperConfig.TableNameOverride.withTableNamePrefix(environment);
        dynamoDBTemplate.setDynamoDBMapperConfig(new DynamoDBMapperConfig(tableNameOverride));

        return dynamoDBTemplate;
    } */
}