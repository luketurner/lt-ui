(ns ulti.icons)

(defn css-rules [theme]
  [[:.icon {:display :inline-block :width "1em" :height "1em" :stroke-width 0 :stroke :currentColor :fill :currentColor}]])

(def default-icons
  {:font ""
   :svg {
         ;; Demo icon from Tabler Icons (https://github.com/tabler/tabler-icons) - MIT Licensed
         :search [:<>
                  [:path {:stroke :none :fill :none :stroke-linecap :round :stroke-linejoin :round :d "M0 0h24v24H0z"}]
                  [:circle {:stroke-width 2 :stroke :currentColor :fill :none :stroke-linecap :round :stroke-linejoin :round :cx 10 :cy 10 :r 7}]
                  [:line {:stroke-width 2 :stroke :currentColor :fill :none :stroke-linecap :round :stroke-linejoin :round :x1 21 :y1 21 :x2 15 :y2 15}]]
         }})

(defn icon-svg-provider []
  [:svg {:display :none}
   (into [:defs]
         (for [[icon-name svg] (:svg default-icons)]
           [:symbol {:id (str "icon-" (name icon-name)) :viewBox "0 0 24 24"} svg]))])

(defn icon [{:keys [icon]}]
  (if (contains? (:svg default-icons) icon)
    [:span.icon [:svg.icon [:use {:href (str "#icon-" (name icon))}]]]
    [:span.icon "No icon found!"]))