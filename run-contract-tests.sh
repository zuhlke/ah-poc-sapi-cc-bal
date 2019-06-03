# Assumption: The fixture has already been deployed to the "development" environment in PCF.

# Clone PAPI repository
git clone https://github.com/zuhlke/ah-poc-papi-springboot.git
# Go to the contract tests
cd ah-poc-papi-springboot/contract-tests
# Run the tests designated for this SAPI against the deployed dev instance of it
./bin/ah-poc-sapi-cc-bal