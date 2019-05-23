# ah-poc-sapi-cc-bal

Springboot SAPI for getting credit card balances.

## Run locally

- It runs on port 3001

`./run-locally`


## Run tests

`./tests`

## Deploy to PCF

Go via the pipeline by pushing to master.

## Travis pipeline

- Pipeline file is `.travis.yml`.
- Environment variables are injected by travis.

## SAPI behaviour Configurations

- Send a request like `/failureRate/100` to set the failure rate. In this case, the failure rate is set to 100%. This will make every request return 500 INTERNAL SERVER ERROR.
  - Note that this number must be an integer between 1 and 100. 

## Configurable SAPI Wish list

- Specify failure rate
- Specify delay for every request, randomly selected within a range
- Delay based on load
- Go offline for random durations
- Random restarts
- Random delay on start

- SAPI starts with a random, non-proper subset of the other problems