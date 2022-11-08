Simple cow farm CLI-application with MVC-like architecture that supports adding new cows and its deletion. 

Cows are stored in format:
- cowId: BigInteger,
- cowName: String,
- parentId: BigInteger.

There are two different collection for cow storing are used.
- CowsMap is based on HashMap realization.
- CowsCollectionImpl backed upg by custom list which uses **neither** Java collections **nor** arrays under the hood.

Run:
- mvn clean install
- mvn exec:java