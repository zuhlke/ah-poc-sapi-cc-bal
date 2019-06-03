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

## Contract tests (WIP)

- Clones the PAPI from GitHub
- Builds the contract test executable using gradle
- Executes the contract tests against itself, verifying that the contract is fulfilled.

#### Notes

- In the case of multiple consumers, this will need to be done for each of them.
- In the case of the PAPI having multiple providers, it will need to either specify different executables, or allow the single executable to have its execution configured by an input parameter, so
that each producer can run the appropriate tests against itself.

## SAPI behaviour Configurations

- Send a request like `/failureRatePc/100` to set the failure rate. In this case, the failure rate is set to 100%. This will make every request return 500 INTERNAL SERVER ERROR.
  - Note that this number must be an integer between 1 and 100.
  
- Send a request like `/perRequestDelayRangeMs?min=100&max=200` to set the per-request random delay range. In this case, there will be a delay for each request between 100ms and 200ms.
  - Note that both the min and max values must be integers, don't give it negative integers because that would be silly. It would also be silly to give a max value that is less than the min value.


## Resetting the SAPI's behaviour to default

- Send a request `/reset` and the currently configured behaviour policy for the SAPI will be reset. This means that no failures will be artificially induced and there will be no artificial delay
on responses.

## Configurable SAPI Wish list

- Specify failure rate
- Specify delay for every request, randomly selected within a range
- Delay based on load
- Go offline for random durations
- Random restarts
- Random delay on start

- SAPI starts with a random, non-proper subset of the other problems