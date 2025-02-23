package com.example.batch.config;

import javax.sql.DataSource;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.example.batch.model.Product;

@Configuration
public class BatchConfig {
	@Value("${sourceFile}")
	private String soruceFile;

//	Product job bean
	@Bean
	public Job productsJob(JobRepository jobRepository, JobCompletionNotificationImpl listener, Step productJobSteps) {
		return new JobBuilder("products_job", jobRepository).listener(listener).start(productJobSteps).build();
	}

//	Steps to execute product job
	@Bean
	public Step productJobSteps(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
			ItemReader<Product> reader, ItemProcessor<Product, Product> processor, ItemWriter<Product> writer) {
		return new StepBuilder("product_step", jobRepository).<Product, Product>chunk(10, transactionManager)
				.allowStartIfComplete(true).reader(reader).processor(processor).writer(writer).build();
	}

//	Reader
	@Bean
	public FlatFileItemReader<Product> reader() {
		return new FlatFileItemReaderBuilder<Product>().name("product_file_reader")
				.resource(new ClassPathResource(soruceFile)).linesToSkip(1).delimited()
				.names("productId", "title", "description", "price", "discount").targetType(Product.class).build();
	}

//	Processor
	@Bean
	public ItemProcessor<Product, Product> processor() {
		return new CustomItemProcessor();
	}

//	Writer
	@Bean
	public ItemWriter<Product> writer(DataSource dataSource) {
		return new JdbcBatchItemWriterBuilder<Product>().sql(
				"insert into products(product_id,title,description,price,discount,discounted_price)values(:productId, :title, :description, :price, :discount, :discountedPrice)")
				.dataSource(dataSource).beanMapped().build();
	}

	@Bean
	public DataSourceTransactionManager transactionManager(DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

}

//  # TO CREATE JOB: 1. JobBuilder 2. listener 3. steps 
//  # TO CREATE STEP: 1. StepBuilder 2. chunk 3. reader 4. processor 5. writer
//  # TO CREATE READER: 1. FlatFileItemReader 2. delimited
//  # TO CREATE WRITER: 1. JdbcBatchItemWriterBuilder 2. sql 3. datasource 4. beanMappe
