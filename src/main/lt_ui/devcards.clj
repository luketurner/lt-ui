(ns lt-ui.devcards
  (:require [devcards.core :refer [defcard]]
            [reagent.core :as reagent]
            [zprint.core :refer [zprint-str]]))

(defn add-code-example [text form]
  (str text "\n\n``` clojure\n" (zprint-str form) "\n```\n"))

(defmacro defcard'
  ([main-obj]
   `(defcard (styled-reagent ~main-obj)))
  ([vname main-obj]
   `(defcard ~vname (styled-reagent ~main-obj)))
  ([vname doc main-obj]
   `(defcard ~vname ~(add-code-example doc main-obj) (styled-reagent ~main-obj)))
  ([vname doc main-obj initial-data]
   `(defcard ~vname ~(add-code-example doc main-obj) (styled-reagent ~main-obj) (reagent/atom ~initial-data)))
  ([vname doc main-obj initial-data opts]
   `(defcard ~vname ~(add-code-example doc main-obj) (styled-reagent ~main-obj) (reagent/atom ~initial-data) ~opts)))
