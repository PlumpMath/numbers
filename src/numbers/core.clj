(ns numbers.core
  (:require [clojure.math.combinatorics :as combo]))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))


(def big-set
  [25 50 75 100])

(def small-set
  [1 , 1 , 2 , 2 , 3 , 3 , 4 , 4 , 5 , 5 , 6 , 6 , 7 , 7 , 8 , 8 , 9 , 9 , 10 , 10])

(def operators
  (mapcat #(repeat 5 %) [* + - /])

  )


(defn select [num-big]
  (concat
  (take num-big (shuffle big-set))
  (take (- 6 num-big) (shuffle small-set))))

(defn target []
   (+ 100 (rand-int 900)))

(target)

(defn gen-sets [numbers]
  (combo/permutations numbers))

(defn try-for-target [numbers operators]
  (loop [result (first numbers)
         numbers (rest numbers)
         operators operators]
    (if (= 0 (count numbers))
      result
      (recur ((first operators) result (first numbers)) (rest numbers) (rest operators)))
    ))


;;(try-for-target '(25 50 3 4 5 6) '(+ - + / *))

(defn test-result [attempt target]
  (= target attempt))

(defn play [num-big]
  (let [numbers (select num-big)
  target-number (target)]

  (for [set (gen-sets numbers)
        ops (combo/combinations operators 5)]
      [(test-result (try-for-target set ops) target) set])
    )

  )

;;(first (filter #(= true (first %)) (play 4)))

(interleave [1 2 3] [4 5 6])
