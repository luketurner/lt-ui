(ns lt-ui.devcards
  (:require [devcards.core :refer [defcard]]
            [reagent.core :as reagent]))

(defmacro defcard'
  ([main-obj]
   `(defcard (styled-reagent ~main-obj)))
  ([vname main-obj]
   `(defcard ~vname (styled-reagent ~main-obj)))
  ([vname doc main-obj]
   `(defcard ~vname ~doc (styled-reagent ~main-obj)))
  ([vname doc main-obj initial-data]
   `(defcard ~vname ~doc (styled-reagent ~main-obj) (reagent/atom ~initial-data)))
  ([vname doc main-obj initial-data opts]
   `(defcard ~vname ~doc (styled-reagent ~main-obj) (reagent/atom ~initial-data) ~opts)))
