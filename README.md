# jptv
A Java wrapper for [Public Transport Victoria](https://ptv.vic.gov.au)'s [timetable API](http://ptv.vic.gov.au/ptv-timetable-api/). This is a proof of concept library, and currently unfinished.

## How to use
Other than to test and help develop the library, you cannot use this library at this time, as breaking changes may happen overtime.

## Roadmap
- [ ] Departures
- [ ] Directions
- [ ] Disruptions
- [ ] Fare estimates
- [ ] Outlets
- [ ] Patterns
- [ ] Routes
- [x] Route types
- [ ] Runs
- [ ] Search
- [ ] Stops

## Developing & Contributing
### Obtaining an API key and developer ID
To test and use this library, you will need an API key from PTV.
To do that, you will need to email `APIKeyRequest@ptv.vic.gov.au` with the subject line of `PTV Timetable API - request for key`.
This may take a few weeks, as it is *not* an automatic process.

Once you have received both the developer ID and API key, you can test the library using the following steps.
### Running tests
To run tests, you will need to utilise the following environment variables:

- `PTV_DEV_ID`: Your seven-digit developer ID, provided by PTV.
- `PTV_API_KEY`: Your API key.

To run the JUnit tests provided, the standard command `./gradlew test` will work.

## License
`jptv` source code is licensed under an [Apache-2.0 licence](https://github.com/wale/jptv/blob/master/LICENSE).


Data is licensed from Public Transport Victoria under a [Creative Commons Attribution 4.0 International Licence](https://creativecommons.org/licenses/by/4.0/).