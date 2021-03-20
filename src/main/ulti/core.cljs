(ns ulti.core
  (:require [reagent.core]
            [garden.core :refer [css]]
            [malli.core :refer [validate explain]]
            [ulti.typography :as typography]
            [ulti.containers :as containers]
            [ulti.inputs :as inputs]
            [ulti.forms :as forms]))

(def theme-schema
  [:map
   [:font-size 'int?
    :line-height 'number?
    :scale-factor 'number?
    :colors [:map {:registry {::color [:alt 'string? 'keyword?]}}
             [:content-fg ::color]
             [:content-bg ::color]
             [:chrome-fg ::color]
             [:chrome-bg ::color]]]])

(def default-theme
  {:font-size 16
   :line-height 1.5
   :scale-factor 1.4
   :colors {:content-fg :black
            :content-bg :white
            :chrome-fg :black
            :chrome-bg :white}})

(defn add-default-values [theme]
  (merge-with (fn [dv v] 
                (if (map? dv)
                  (merge dv v)
                  v)) default-theme theme))

(defn theme-rules [theme]
  (let [theme (add-default-values theme)]
    (when-not (validate theme-schema theme)
      (throw (explain theme-schema theme)))
    (concat [[:* {:box-sizing :border-box}]]
            (typography/rules theme)
            (containers/rules theme)
            (inputs/css-rules theme))))

(defn themed-stylesheet [theme]
  [:style (css (theme-rules theme))])

