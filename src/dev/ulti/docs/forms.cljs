(ns ulti.docs.forms
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [ulti.forms :as forms]
            [ulti.inputs :as inputs]
            [ulti.containers :as containers]
            [clojure.string :as string]))

(defcard' form-group
  "Use the `forms/group` function to organize inputs into a traditional form layout.
   
   Form groups expect a :label, which in this example is a string, but could also be a Hiccup form.
   They also expect a vector of Hiccup forms in the :inputs prop. The specified inputs will be
   rendered on top of each other alongside the label."
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))]
      [forms/group {:label "Username"
                  :inputs [[inputs/text (ulti.inputs/cursor data on-change
                                                            [:username])]]}]))
  {:username nil}
  {:inspect-data true})

(defcard' form-group-helptext
  "An example of using a more complex label -- this one has help text in a popover."
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))]
      [forms/group {:label [:<> "Username" [containers/popover
                                            {:handle [:strong " (?)"]}
                                            [containers/paper "Username help text!"]]]
                    :inputs [[inputs/text (ulti.inputs/cursor data on-change
                                                              [:username])]]}]))
  {:username nil}
  {:inspect-data true})

(defcard' form-group-validation
  "The `group` component sets an `on-error` handler for its :inputs. Input errors are rendered below the label.
   
   In this case, strings that contain a `q` are considered valid."
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))
          validator (fn [v] (string/includes? v "q"))
          show-error (fn [] "Must contain a 'q'")]
      [forms/group {:label "Username"
                    :inputs [[inputs/text (ulti.inputs/cursor data on-change
                                                              [:username]
                                                              {:validator validator
                                                               :show-error show-error})]]}]))
  {:username nil}
  {:inspect-data true})