(ns battleline.common
  (:require [clojure.spec.alpha :as spec]
            [clojure.spec.gen.alpha :as gen]
            [clojure.test.check.generators :as check]))

(defn shared-fn
  "A function that is shared between clj and cljs"
  []
  (println "cljc!"))

(def color? #{:r :g :y :b :o :p})

(def value? (range 1 11))

(def deck (for [color color? value value?] [color value]))

(spec/def ::card (spec/tuple color? (spec/int-in 1 11)))

(spec/def ::formation (spec/and
                        (spec/coll-of ::card :min-count 0 :max-count 3)
                        distinct?))


(def empty-flag [[] []])

(def empty-board (repeat 9 empty-flag))

(spec/def ::flag (spec/cat :player1-troops ::formation :player2-troops ::formation))

(spec/def ::formation-cards (spec/and nat-int? #(< % 4)))

(spec/def ::board (spec/and
                    (spec/coll-of ::flag :count 9)))

(defn deal-hands [[deck hand-sizes]]
  ;;(println deck)
  ;;(println hand-sizes)
  (first (reduce (fn [[hands deck] hand-size]
                   [(conj hands (take hand-size deck)) (drop hand-size deck)])
                 [[] deck]
                 hand-sizes)))

(defn make-board [hands]
  (partition 2 hands))

(def board-gen
  (gen/fmap (comp make-board deal-hands)
            (gen/tuple (check/shuffle deck)
                       (gen/vector (spec/gen ::formation-cards) 18))))


(comment
  (gen/generate (spec/gen ::card))
  (gen/sample (spec/gen ::card))
  (gen/sample (spec/gen ::formation))
  (gen/sample (spec/gen ::formation-cards))
  (gen/sample (spec/gen ::board))
  (gen/sample (check/shuffle deck))
  (gen/sample check/nat)
  (gen/sample board-gen)
  (time (deal-hands [(shuffle deck) (gen/sample (spec/gen ::formation-cards) 18)]))
  (spec/conform ::board empty-board)
  (spec/conform ::board (gen/generate board-gen)))
