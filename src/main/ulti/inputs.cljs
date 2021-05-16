(ns ulti.inputs
  (:require [reagent.core :as reagent]
            [garden.units :refer [px]]
            [garden.selectors :as s]
            [ulti.containers :as containers]
            [clojure.string :as string]
            [ulti.util :refer-macros [defcomponent]])
  (:refer-clojure :exclude [time]))

(defn css-rules [{:keys [line-height font-size]}]
  (let [m-px (-> line-height (* font-size) (js/Math.floor))]
    [[:.invalid-input {:border-color "red"}]
     [(s/> :.input-group :*) {:margin-left 0 :margin-right 0}]
     [((s/> :.input-group :*) (s/not s/last-child)) {:border-right 0}]
     [:.combobox-item {:padding "2px" :cursor :pointer}]]))

(defcomponent group [props children]
  (into [:div.input-group] (map #(update % 1 merge props) children)))


(defn combobox-impl [{:keys [options value on-change]}]
  (reagent/with-let [expanded? (reagent/atom nil)]
    (let [is-match? (fn [{v :value l :label}]
                      (and
                       (string? value)
                       (not= value v)
                       (or
                        (string/includes? (string/lower-case l) (string/lower-case value))
                        (string/includes? v value))))
          matching-options (filter is-match? options)]
     [:div.combobox {:on-focus #(reset! expanded? true)
                     :on-blur #(reset! expanded? false)}
     [:input {:type :text
              :value value
              :on-change #(-> % (.-target) (.-value) (on-change))}]
     [containers/popover {:value (and @expanded? (seq matching-options))}
      (into
       [containers/paper]
       (for [{v :value label :label} matching-options]
         [:div.combobox-item {:tabindex 0
                              :on-click #(do (reset! expanded? false)
                                             (on-change v))}
          label]))]])))

(defn input [{:keys [type options value on-change validator on-error show-error]
              :or {validator #(identity true)
                   show-error #(str "Invalid value.")
                   on-error #(identity true)
                   on-change #(identity true)}
              :as props}]

  ;; initialize atoms for managing state. Maintains two versions of the value:
  ;;   internal-value-atom :: contains the current raw input (which may not be valid.)
  ;;   external-value-atom :: contains the latest value that was valid and sent out with on-change
  (reagent/with-let [internal-value-atom (reagent/atom nil)
                     external-value-atom (reagent/atom nil)]

    ;; if the caller specifies a different `value`, there was an external update --
    ;; something other than this input has changed the backing value. So, we update
    ;; our internal state to match.
    ;; (this triggers the component to re-render)
    (when (not= value @external-value-atom)
      (reset! external-value-atom value)
      (reset! internal-value-atom value))

    ;; now that side effects are done, proceed with building the Reagent component
    (let [internal-value @internal-value-atom
          value-is-valid? (or (nil? internal-value) (validator internal-value))
          on-change #(if (validator %)
                       (do
                         (reset! internal-value-atom %)
                         (reset! external-value-atom %)
                         (on-change %)
                         (on-error nil))
                       (do
                         (reset! internal-value-atom %)
                         (on-error (show-error %))))
          on-change-el #(-> % (.-target) (.-value) (on-change))
          props (merge props {:on-change on-change
                              :value internal-value
                              :class (str (when-not value-is-valid? "invalid-input"))})
          props-el (-> props
                       (assoc :on-change on-change-el)
                       (dissoc :validator :on-error :show-error))]
      (case type
        :textarea [:textarea props-el]
        :select (into [:select props-el] (for [o options]
                                       [:option {:value (:value o)} (:label o)]))
        :combobox [combobox-impl props]
        [:input props-el]))))


;; Functions for all the <input> types
(defn checkbox [props] [input (assoc props :type :checkbox)])
(defn color [props] [input (assoc props :type :color)])
(defn date [props] [input (assoc props :type :date)])
(defn datetime-local [props] [input (assoc props :type :datetime-local)])
(defn email [props] [input (assoc props :type :email)])
(defn file [props] [input (assoc props :type :file)])
(defn month [props] [input (assoc props :type :month)])
(defn integer [props] [input (assoc props :type :number)])
(defn number [props] [input (assoc props :type :number)])
(defn password [props] [input (assoc props :type :password)])
(defn radio [props] [input (assoc props :type :radio)]) ; TODO
(defn numeric-range [props] [input (assoc props :type :range)])
(defn search [props] [input (assoc props :type :search)]) ; TODO?
(defn tel [props] [input (assoc props :type :tel)])
(defn text [props] [input (assoc props :type :text)])
(defn time [props] [input (assoc props :type :time)])
(defn url [props] [input (assoc props :type :url)])
(defn week [props] [input (assoc props :type :week)])

;; functions for custom input types
(defn select [props] [input (assoc props :type :select)])
(defn combobox [props] [input (assoc props :type :combobox)])
(defn textarea [props] [input (assoc props :type :textarea)])


(defn cursor
  ([data on-change path] (cursor data on-change path {}))
  ([data on-change path props]
   (merge props
          {:value (get-in data path)
           :on-change #(on-change (assoc-in data path %))})))
