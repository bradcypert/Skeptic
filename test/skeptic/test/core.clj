(ns skeptic.test.core
    (:require [clojure.test :refer :all]
              [skeptic.core :as s]))

; These tests run asserts so they're going to return nil. We just want to make those asserts pass
; So we'll create simple test cases to ensure no exceptions are thrown.
(deftest core-tests
    (testing ":expect should work alone"
        (is (= (s/test "4 should always equal 4"
                :expect (= 4 4) true) nil)))

     (testing ":given with :expect works as expected"
         (is (= (s/test "Given A is 4, expect A to be 4."
                :given [a 4]
                :expect (= a 4)) nil))))