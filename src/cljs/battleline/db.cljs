(ns battleline.db
  (:require [battleline.common :as common]
            [cljs.spec.alpha :as spec]
            [cljs.spec.gen.alpha :as gen]))

(def default-db
  {:name  "re-frame"
   :board (spec/conform :battleline.common/board (gen/generate common/board-gen))})
