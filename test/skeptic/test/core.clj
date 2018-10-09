(ns skeptic.test.core
  (:require [clojure.test :refer :all]
            [skeptic.core :as s]))

;; These tests run asserts so they're going to return nil. We just want to make those asserts pass
;; So we'll create simple test cases to ensure no exceptions are thrown.
(deftest core-tests
  (testing ":expect should work alone"
    (is (= (s/test "4 should always equal 4"
                   :expect (= 4 4) true) nil)))

  (testing ":given with :expect works as expected"
    (is (= (s/test "Given A is 4, expect A to be 4."
                   :given [a 4]
                   :expect (= a 4)) nil)))

  (testing "no :expect should throw an exception"
    (is (thrown-with-msg? Exception
                          #"No Expectation Specified. Ensure that your test options includes an :expect"
                          (eval '(skeptic.core/test "Given A is 4..."
                                                    :given [a 4])))))
  (testing ":given should allow multiple let bindings"
    (is (= (s/test "Given A is 4, and B is 5, expect A to be 4"
                   :given [a 4
                           b 5]
                   :expect (= a 4)) nil)))

  (testing ":given should allow var rebinding"
    (is (= (s/test "Given A is 4, and A is 5, expect A to be 5"
                   :given [a 4
                           a 5]
                   :expect (= a 5)) nil)))

  (testing "it should fail if given a false expectation"
    (is (thrown? AssertionError (eval '(skeptic.core/test "Expect 4 to be 5"
                                                          :expect (= 4 5))))))

  (testing ":when should do mutations to :given variables"
    (is (= (s/test "Given A is 4, when A is incremented, expect A to be 5"
                   :given [a (atom 4)]
                   :when (swap! a inc)
                   :expect (= @a 5)) nil)))

  (testing "recreating the example in the docs"
    (let [->User (fn [firstname lastname] (atom {:firstname firstname
                                                 :lastname lastname}))
          setLastName (fn [user lastname] (swap! user assoc :lastname lastname))
          getLastName (fn [user] (:lastname @user))]
      (is (= (s/test "update their last name"
                     :given [user (->User "Frank" "Jonson")]
                     :when (setLastName user "Johnson")
                     :expect (= (getLastName user) "Johnson")) nil)))))
