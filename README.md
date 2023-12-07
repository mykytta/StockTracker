# StockTracker

## Overview

StockTracker is a Java Spring Boot application designed for real-time monitoring and analysis of financial markets. It fetches data from an open-source Stocks feed API, processes it asynchronously using multiple threads, and stores the information in a database for further analysis. The application focuses on efficiency, scalability, and providing valuable insights into the stock market.

## Project Details

- **Technology Stack:**
  - Java Spring Boot
  - MySQL
  - AWS
  - Multithreading
  - CI/CD

## Key Features

- **Data Collection:**
  - On application load, queries an open-source Stocks feed API to fetch symbols/names/state for each trading company.

- **Asynchronous Processing:**
  - Utilizes multithreading to concurrently process data for each enabled company, improving responsiveness and performance.

- **Database Storage:**
  - Persists stock data in MySQL, allowing for historical analysis and logging changes for each company over time.

- **AWS Deployment:**
  - Deployed to AWS for seamless scalability, ensuring reliability and responsiveness in handling large datasets.

- **Continuous Integration/Continuous Deployment (CI/CD):**
  - Established a robust CI/CD pipeline for continuous updates and deployments, optimizing development and release processes.
