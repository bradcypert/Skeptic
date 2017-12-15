(ns skeptic.core)

(def errors {
             :no-expect "No Expectation Specified. Ensure that your test options includes an :expect."
             })

(defn- human-to-kebab [s]
  (clojure.string/replace s #" " "-"))

;(test “update their last name” {
;      :given   [user (->User “Frank” “Jonson”)]
;      :when    (setLastName user “Johnson”)
;      :expect  (= (getLastName user) “Johnson”)})

(defmacro test
  "skeptic.test/test
   (test-name options)
   Defines a new test with the given assertions
   the options map takes up-to 3 key value pairs:
   :given, :when, :expect
   :given  [optional] - a vector representing a let binding to be used in your test.
   :when   [optional] - a list representing any transformations that the test should occur.
   :expect [required] - a list representing any expectations that should occur. These are placed within an assert."
  [test-name & args]
  (let [options (into {} (vec (map vec (partition 2 args))))
        givenf#  (:given options)
        whenf#   (:when options)
        expectf# (:expect options)]
  (cond
    (nil?  expectf#) (throw (ex-info (:no-expect errors) {:expect expectf#}))
    :default `(let ~givenf#
               ~whenf#
               (assert ~expectf# (str ~test-name " failed the given expectation: " (quote ~expectf#)))))))
