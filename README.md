# skeptic

Given-When style testing for Clojure.

Tests are one of the best forms of code documentation, but are often hard to read or even comprehend. Structuring your tests as documentation gives you the best of both worlds.

[![Build Status](https://travis-ci.org/bradcypert/Skeptic.svg?branch=master)](https://travis-ci.org/bradcypert/Skeptic)

## Usage
```clojure
(suite “Users should be able to”
  (test “get a localized version of their name”     
    :given [user (->User “Brad” “Cypert”)]
    :when (get-localized-name user)
    :expect “Brad Cypert”))

(suite “Tests about the number 4”
  (test “4 should always equal 4”
    :expect (= 4 4))

  (test “Given A is 4, a should equal 4”
    :given [a 4]
    :expect (= a 4)))
```
## License

Copyright © 2016 Brad Cypert
MIT
