(ns ulti.containers
  (:require [garden.selectors :as s]
            [garden.units :refer [vh vw rem px]]
            [reagent.core :as reagent]))

(defn css-rules [{:keys [line-height font-size colors]}]
  (let [m-px (-> line-height (* font-size) (js/Math.floor))]
    [[:.application {:min-height (vh 100)
                     :width "100%"
                     :padding (px m-px)
                     :display "grid"
                     :grid-template-rows "fit-content(5ch) 1fr fit-content(5ch)"
                     :grid-template-columns "fit-content(20ch) minmax(min(50vw, 30ch), 1fr) fit-content(20ch)"
                     :grid-template-areas "\"header header header\" \"left   main   right\" \"footer footer footer\""
                     :gap (px m-px)
                     :color (:chrome-fg colors)
                     :background-color (:chrome-bg colors)}
      [(s/> :.sidebar-left) {:grid-area :left}]
      [(s/> :.sidebar-right) {:grid-area :right}]
      [(s/> :footer) {:grid-area :footer}]
      [(s/> :header) {:grid-area :header}]
      [(s/> :main) {:grid-area :main
                    :color (:content-fg colors)
                    :background-color (:content-bg colors)}]]
     [:.centered {:display :grid :place-content :center}]
     [:.vertical-split {:display :flex :flex-flow "row nowrap"}
      [(s/> :*) {:flex-grow 1}]]
     [:.horizontal-split {:display :flex :flex-flow "column nowrap"}
      [(s/> :*) {:flex-grow 1}]]
     [:.paper {:background-color (:chrome-bg colors)
               :box-shadow ["0px 2px 1px -1px rgba(0, 0, 0, 0.2)" ; based on md box shadow
                            "0px 1px 1px 0px rgba(0, 0, 0, 0.14)"
                            "0px 1px 3px 0px rgba(0,0,0,.12)"]
               :padding (px m-px)
               :border-radius "5px"}
      [(s/> (s/last-child)) {:margin-bottom "0px"}]]
     [:.popover-container {:position :relative :z-index 1000}]
     [:.popover-handle {:cursor :pointer}]
     [:.popover-content {:position :absolute}]
     [:.utility-application-demo {:min-height (px 300)}]
     [:.utility-fill-color {:background-color "rgba(150, 200, 255, 0.5)" :width "100%" :height "100%"}]]))

(defn application [props & children]
  (let [props? (map? props)
        children (if props? children (into [props] children))
        props (if props? props {})
        {:keys [sidebar-left sidebar-right header footer]} props
        props (dissoc props :sidebar-left :sidebar-right :header :footer)]
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

(defn paper [& children]
  (into [:div.paper] children))

(defn popover [{:keys [handle value on-change]} & children]
  (reagent/with-let [open? (reagent/atom false)
                     last-value (reagent/atom false)]
    (when (not= value @last-value) (reset! open? value) (reset! last-value value))
    [:span.popover-container
     [:span.popover-handle {:on-click #(do 
                                        (swap! open? not)
                                        (when (fn? on-change) (on-change @open?)))}
      handle]
     (when @open?
       (into [:div.popover-content] children))]))