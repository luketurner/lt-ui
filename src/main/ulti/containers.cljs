(ns ulti.containers
  (:require [garden.selectors :as s]
            [garden.units :refer [vh vw rem px]]
            [reagent.core :as reagent]))

(defn rules [{:keys [line-height font-size]}]
  (let [m-px (-> line-height (* font-size) (js/Math.floor))]
    [[:.application {:min-height (vh 100)
                     :width "100%"
                     :padding (px m-px)
                     :display "grid"
                     :grid-template-rows "25px 1fr 25px"
                     :grid-template-columns "fit-content(20ch) minmax(min(50vw, 30ch), 1fr) fit-content(20ch)"
                     :grid-template-areas "\"header header header\" \"left   main   right\" \"footer footer footer\""
                     :gap (px m-px)}
      [(s/> :.sidebar-left) {:grid-area :left}]
      [(s/> :.sidebar-right) {:grid-area :right}]
      [(s/> :footer) {:grid-area :footer}]
      [(s/> :header) {:grid-area :header}]
      [(s/> :main) {:grid-area :main}]]
     [:.centered {:display :grid :place-content :center}]
     [:.vertical-split {:display :flex :flex-flow "row nowrap"}
      [(s/> :*) {:flex-grow 1}]]
     [:.horizontal-split {:display :flex :flex-flow "column nowrap"}
      [(s/> :*) {:flex-grow 1}]]
     [:.popover-container {:position :relative}]
     [:.popover-handle {:cursor :pointer}]
     [:.popover-content {:position :absolute :background-color :white :border "1px solid gray" :padding "5px" :border-radius "5px"}]
     [:.utility-application-demo {:min-height (px 300)}]
     [:.utility-fill-color {:background-color "rgba(150, 200, 255, 0.5)" :width "100%" :height "100%"}]]))

(defn application [props & children]
  (let [props? (map? props)
        children (if props? children (into [props] children))
        props (if props? props {})
        {:keys [sidebar-left sidebar-right header footer]} props]
    [:div.application props ; TODO -- filter props
     [:header header]
     [:div.sidebar-left sidebar-left]
     (into [:main] children)
     [:div.sidebar-right sidebar-right]
     [:footer footer]]))

(defn router [{:keys [views value]}]
  (get views value))

(defn centered [& children]
  (into [:div.centered] children))

(defn vertical-split [& children]
  (into [:div.vertical-split] children))

(defn horizontal-split [& children]
  (into [:div.horizontal-split] children))

(defn popover [{:keys [handle value on-change]} & children]
  (reagent/with-let [open? (reagent/atom false)
                     last-value (reagent/atom false)]
    (when (not= value @last-value) (reset! open? value) (reset! last-value value))
    [:div.popover-container
     [:div.popover-handle {:on-click #(do 
                                        (swap! open? not)
                                        (when (fn? on-change) (on-change @open?)))}
      handle]
     (when @open?
       (into [:div.popover-content] children))]))