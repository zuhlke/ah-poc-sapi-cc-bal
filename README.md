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

## Configurable SAPI Wish list

- Specify failure rate
- Specify delay for every request, randomly selected within a range
- Delay based on load
- Go offline for random durations
- Random restarts
- Random delay on start

- SAPI starts with a random, non-proper subset of the other problems