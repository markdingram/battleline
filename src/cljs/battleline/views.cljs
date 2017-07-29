(ns battleline.views
    (:require [re-frame.core :as re-frame]))

(def colours {:r "red"
              :g "green"
              :y "yellow"
              :b "blue"
              :o "orange"
              :p "purple"})


(defn main-panel []
  (let [name (re-frame/subscribe [:name])
        board (re-frame/subscribe [:board])]
    (fn []
      [:svg {:width 1000 :height 1000}
       (for [[index flag] (map-indexed vector @board)]
         (let [{:keys [player1-troops]} flag
               x (+ 10 (* 100 index))]
           ;;(println player1-troops)
           (for [[index troop] (map-indexed vector player1-troops)]
             (let [y (+ 10 (* 100 index))
                   [troop-colour troop-number] troop]
               [:g
                [:rect {:x x :y y :width 80 :height 80 :style {:fill (get colours troop-colour) :stroke "black"}}]
                [:text {:x (+ x 40) :y (+ y 40) :text-anchor "middle" :alignment-baseline "central"} troop-number]]))))
       (for [[index flag] (map-indexed vector @board)]
         (let [cx (+ 50 (* 100 index))
               cy 350]
           [:circle {:cx cx :cy cy :r 15 :stroke "black" :fill "red"}]))
       (for [[index flag] (map-indexed vector @board)]
         (let [{:keys [player2-troops]} flag
               x (+ 10 (* 100 index))]
           ;;(println player2-troops)
           (for [[index troop] (map-indexed vector player2-troops)]
             (let [y (+ 410 (* 100 index))
                   [troop-colour troop-number] troop]
               [:g
                [:rect {:x x :y y :width 80 :height 80 :style {:fill (get colours troop-colour) :stroke "black"}}]
                [:text {:x (+ x 40) :y (+ y 40) :text-anchor "middle" :alignment-baseline "central"} troop-number]]))))])))
