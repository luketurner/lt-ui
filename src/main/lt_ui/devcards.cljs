(ns lt-ui.devcards
  (:require [devcards.core :refer [defcard reagent]]
            [reagent.core :as reagent]))

(def styles (reagent/atom ""))

(defn defcard' [el]
  (defcard (reagent [:<>
                     [:style @styles]
                     el])))

(defn init! []
  (devcards.core/start-devcard-ui!)
  (println "Deleting com-rigsomelight-devcards-addons-css element...")
  (.remove (js/document.getElementById "com-rigsomelight-devcards-addons-css")))