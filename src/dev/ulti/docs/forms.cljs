(ns ulti.docs.forms
  (:require [ulti.docs.devcards :refer-macros [defcard']]
            [ulti.forms :as forms]
            [ulti.inputs :as inputs]
            [ulti.containers :as containers]
            [clojure.string :as string]))

(defcard' form
  "The `form` component is used to encapsulate inputs into a form. The state of the inputs are tracked in a single combined data structure
   which represents the last valid state of the form inputs. When the form is submitted, the passed-in `on-submit` function will
   be called with the full data structure.
   
   Instead of accepting children, the form accepts a single function parameter that comes after the options. This function is expected to return
   the form's children. The function receives a map parameter with keys `:data`, `:getter`, and `:setter` that can be used to interact with the
   form's data structure. It also has the key `:submit` which contains a function that can be used to submit the form.
   
   Note that submit butttons are not added automatically -- you have to include one in your function's return value for the form to be submittable.
   For example:"
  (fn [data-atom]
    (let [on-submit (fn [data] (reset! data-atom data))]
      [forms/form {:on-submit on-submit}
       (fn [{:keys [getter setter submit]}]
         [:<>
          [inputs/text {:value (getter [:foo]) :on-change (setter [:foo])}]
          [:button {:on-click submit} "Submit"]])]))
  {}
  {:inspect-data true})

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

(defcard' form-group-multiple-inputs
  "Multiple inputs can be included in a single form group. In this case, errors from any of the
   provided inputs will be displayed (and if a new error occurs in a different input, it will clobber
   the old error.) However, only the affected input becomes red, so it's clear which input in the group
   is invalid.
   
   The point of grouping multiple inputs would be if a single label/helptext applied to them all. For
   example, if you're asking the user for their address, you may use a single `group` for all the address
   inputs.
   
   In this example, the address editor has two lines, which are independently validated. For demo purposes,
   the validation errors if the line has content, but no digits."
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))
          validator (fn [v] (or (empty? v) (re-find #"[0-9]" v)))
          show-error (fn [v] (str "Invalid address: " v))]
      [forms/group {:label "Address"
                    :inputs [[inputs/text (ulti.inputs/cursor data on-change
                                                              [:address :line 0]
                                                              {:validator validator
                                                               :show-error show-error})]
                             [inputs/text (ulti.inputs/cursor data on-change
                                                              [:address :line 1]
                                                              {:validator validator
                                                               :show-error show-error})]]}]))
  {:address {}}
  {:inspect-data true})

(defcard' form-group-with-input-groups
  "If you want to combine multiple inputs side-by-side on the same line, you should use the `group`
   function from the `ulti.inputs` namespace. Input groups and form groups can be used together --
   in this case, to put a First/Last name editor on the same line."
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))]
      [forms/group {:label "Name"
                    :inputs [[inputs/group {}
                              [inputs/text (ulti.inputs/cursor data on-change
                                                               [:name :first])]
                              [inputs/text (ulti.inputs/cursor data on-change
                                                               [:name :last])]]]}]))
  {:name {}}
  {:inspect-data true})

(defcard' form-with-groups-example
  "An example of using `form` and `group` together to provide a traditional form interface."
  (fn [data-atom]
    (let [on-submit (fn [data] (reset! data-atom data))]
      [forms/form {:on-submit on-submit}
       (fn [{:keys [getter setter submit]}]
         [:<>
          [forms/group {:label "Foo" :inputs [[inputs/text {:value (getter [:foo]) :on-change (setter [:foo])}]]}]
          [forms/group {:label "Bar" :inputs [[inputs/text {:value (getter [:bar]) :on-change (setter [:bar])}]]}]
          [:button {:on-click submit} "Submit"]])]))
  {}
  {:inspect-data true})