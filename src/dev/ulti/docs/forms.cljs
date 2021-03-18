(ns ulti.docs.forms
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [ulti.forms :as forms]
            [ulti.inputs :as inputs]
            [clojure.string :as string]))

(defcard' form-group
  "Use the `forms/group` function to organize inputs into a traditional form layout.
   
   Form groups expect a :label, which in this example is a string, but could also be a Hiccup form.
   They also expect a vector of Hiccup forms in the :inputs prop. The specified inputs will be
   rendered on top of each other alongside the label."
  (fn [data-atom]
    [forms/group {:label "Username"
                  :inputs [[inputs/text (ulti.inputs/cursor @data-atom
                                                             #(reset! data-atom %)
                                                             [:username])]]}])
  {:username nil}
  {:inspect-data true})

(defcard' form-group-validation
  "The `group` component sets an `on-error` handler for its :inputs. Input errors are rendered below the label.
   
   In this case, strings that contain a `q` are considered valid."
  (fn [data-atom]
    [forms/group {:label "Username"
                  :inputs [[inputs/text (ulti.inputs/cursor @data-atom
                                                             #(reset! data-atom %)
                                                             [:username]
                                                             {:validator #(string/includes? % "q")
                                                              :show-error #(str "Must contain q")})]]}])
  {:username nil}
  {:inspect-data true})