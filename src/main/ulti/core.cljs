(ns ulti.core
  (:require [reagent.core]
            [garden.core :refer [css]]
            [malli.core :refer [validate explain]]
            [ulti.typography :as typography]
            [ulti.containers :as containers]
            [ulti.icons :as icons]
            [ulti.inputs :as inputs]
            [ulti.forms :as forms]))

(def global-css-rules [[:* {:box-sizing :border-box}]])

(def theme-schema
  [:map
   [:font-size 'int?
    :line-height 'number?
    :scale-factor 'number?
    :color [:map {:registry {::color [:alt 'string? 'keyword?]
                              ::theme [:map
                                       [:fg ::color]
                                       [:bg ::color]]}}
             [:content ::theme]
             [:chrome ::theme]]]])

(def default-theme
  {:font-size 16
   :line-height 1.5
   :scale-factor 1.4
   :color
   {:content {:fg :black
              :bg :wnite}
    :chrome  {:fg :black
              :bg :white}}})

(defn add-default-values [theme]
  (merge-with (fn [dv v]
                (if (map? dv)
                  (merge dv v)
                  v)) default-theme theme))

(defn validate-theme [theme]
  (when-not (validate theme-schema theme)
    (throw (explain theme-schema theme))))

(defn theme-rules [theme]
  (let [theme (add-default-values theme)]
    (validate-theme theme)
    (concat global-css-rules
            (typography/css-rules theme)
            (containers/css-rules theme)
            (icons/css-rules theme)
            (inputs/css-rules theme)
            (forms/css-rules theme))))

(defn themed-stylesheet [theme]
  [:style (css (theme-rules theme))])