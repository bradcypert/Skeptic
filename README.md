# skeptic

Given-When style testing for Clojure.

Tests are one of the best forms of code documentation, but are often hard to read or even comprehend. Structuring your tests as documentation gives you the best of both worlds.

## Usage
```clojure
(suite “Users should be able to”
  (test “get a localized version of their name”     
    :given [user (->User “Brad” “Cypert”)]
    :when (getLocalizedName user)
    :expect “Brad Cypert”)
  
  (test “update their last name”
    :given [user (->User “Frank” “Jonson”)]
    :when (setLastName user “Johnson”)
    :expect (= (getLastName user) “Johnson”))

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
