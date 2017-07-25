(ns battleline.views
    (:require [re-frame.core :as re-frame]))

(defn main-panel []
  (let [name (re-frame/subscribe [:name])]
    (fn []
      [:div
       [:div "Hello from " @name]
       [:svg {:width 500 :height 500}
         [:g
          [:rect {:x 10 :y 10 :width 80 :height 80 :style {:fill "rgb(0,0,255)" :stroke-width 3 :stroke "rgb(255,255,255)"}}]
          [:text {:x 50 :y 50 :text-anchor "middle" :alignment-baseline "central"} "7"]]
         [:g
          [:rect {:x 110 :y 10 :width 80 :height 80 :style {:fill "rgb(0,255,0)" :stroke-width 3 :stroke "rgb(255,255,255)"}}]
          [:text {:x 150 :y 50 :text-anchor "middle" :alignment-baseline "central"} "6"]]]])))