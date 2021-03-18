(ns ulti.core
  (:require [reagent.core]
            [garden.core :refer [css]]
            [ulti.typography :as typography]
            [ulti.containers :as containers]
            [ulti.inputs :as inputs]
            [ulti.forms :as forms]))

(defn theme-rules [theme]
  (concat [[:* {:box-sizing :border-box}]]
          (typography/rules theme)
          (containers/rules theme)
          (inputs/css-rules theme)))

(defn themed-stylesheet [theme]
  [:style (css (theme-rules theme))])

