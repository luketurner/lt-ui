(ns lt-ui.docs.inputs
  (:require [lt-ui.devcards :refer-macros [defcard']]
            [devcards.core :refer [defcard]]
            [lt-ui.inputs :as inputs]
            [clojure.string :as string]))

(defcard overview
  "### Overview
   
   The `lt-ui.inputs` namespace provides a suite of React components that
   provide some advanced functionality on top of bare `<input>` elements.
   
   Basic usage of input components is to specify `value` and `on-change` props,
   just like you would for a raw `<input>` that's being controlled by React.
   
   The
   only difference is that the `on-change` function is called with a pre-parsed value of the
   approriate type (e.g. a string for text inputs) instead of having to deal with the SyntheticEvent directly.
   
   Usually, the inputs namespace should be imported with `:as`, for example:

   ``` clojure
   (require '[lt-ui.inputs :as inputs])
   ```

   A simple usage example:
   
   ``` clojure
   [inputs/input {:type :text
                  :value \"example value\"
                  :on-change #(println \"new value: \" %)}]
   ```

   This uses the `input` component, which requires the input type
   to be specified in the `type` prop.
   
   Common input types like `text` also have shorthand functions
   defined for them, so we could equivalently write:

   ``` clojure
   [inputs/text {:value \"example value\"
                 :on-change #(println \"new value: \" %)}]
   ```

   ### Supported input types

   - `text`

   ### About data-atom
   
   Later code examples you'll see are written as functions that accept a `data-atom` parameter,
   and define their `value` and `on-change` props accordingly. For example:

   ``` clojure
   (fn [data-atom]
     (let [data @data-atom
           on-change (fn [data] (reset! data-atom data))]
       [inputs/text {:value data
                     :on-change on-change}]))
   ```

   Thanks to the magic of the `devcards` framework, this lets the examples be fully interactive,
   right here in the docs.
   But it might not exactly reflect the way that you intend to populate those values in your app.
   A more realistic example of `value` and `on-change` using `re-frame` would be something like this:

   ``` clojure
   (require '[lt-ui.inputs :as inputs])
   (require '[re-frame.core :refer [subscribe dispatch]])

   [inputs/text {:value @(subscribe [:my-data])
                 :on-change #(dispatch [:update-my-data %])}]
   ```
   
   So, just keep that in mind as we proceed through the more complex examples.")

(defcard' basic-text-input
  "### Text input
   This is an example of a basic text input in action.
   Any changes to the value of the input is reflected in the `data-atom` (shown below the input.)"
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))]
     [inputs/text {:value data
                   :on-change on-change}]))
  nil
  {:inspect-data true})

(defcard' input-cursors
  "### Cursors
   
   Because the `value` and `on-change` props are often related
   (e.g. by navigating to the same point in a data structure, to read and write respectively)
   there is a helper function `cursor`.
   
   The following are functionally equivalent:
   
   ``` clojure
   (defn person-editor [person on-change]
     [inputs/text (inputs/cursor person on-change [:name])])
   
   (defn person-editor [person on-change]
     [inputs/text {:value (get-in person [:name])
                   :on-change #(on-change (assoc-in person [:name] %))}])
   ```

   This can significantly reduce line noise for more complex editors:
   
   ``` clojure
   (defn person-editor [person on-change]
     [:<>
       [inputs/text (inputs/cursor person on-change [:name])]
       [inputs/text (inputs/cursor person on-change [:birth-date])]
       [inputs/text (inputs/cursor person on-change [:phone-number])]])
   ```

   An important note about this `cursor` is that it's *not* a \"real\" cursor like
   `reagent.core/cursor`. It doesn't use atoms and it's not deref-able.
   Instead, you must specify an explicit state change propagation function with
   the `on-change` parameter to the cursor.
   
   So, `cursor` is purely a convenience for constructing the `value` and `on-change`
   props in a consistent fashion. It's specifically intended for constructing reusable
   components for editing data structures (e.g. the `person-editor` in the example above).

   ### Extra Options

   For convenience, the cursor function accepts an optional `opts` argument that can be used
   to pass options through to the input.
   
   The following are functionally equivalent:

   ``` clojure
   (defn person-editor [person on-change]
     [inputs/text (inputs/cursor person on-change [:name] {:class \"myclass\"})])
   
   (defn person-editor [person on-change]
     [inputs/text (merge {:class \"myclass\"} (inputs/cursor person on-change [:name]))])
   ```

   ### Interactive Example

   Here's an interactive example that uses `cursor` to set the `:value` key of `data-atom`
   to the input value. Note that we have to specify an `on-change` function, but that function
   is called with the *entire updated contents*, rather than just the updated :value key. This is
   the value of the cursor."
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))]
      [inputs/text (inputs/cursor data on-change [:value])]))
  {:value nil :error nil}
  {:inspect-data true})

(defcard' input-validators
  "### Validators
   
   Inputs can be validated by specifying a `validator` function.
   If the validator returns false for a value, the input's `on-change` will not be called.
   
   ``` clojure
   (defn username-valid? [value]
     ;; pretend this returns true if username is valid
     )

   (defn username-editor [value on-change]
     [inputs/text {:value value
                   :on-change on-change
                   :validator username-valid?}])
   ```
   
   Invalid input is maintained as hidden, transient state (Reagent atoms) within the input component.
   If the `value` is updated externally (e.g. by some other event in the system),
   the invalid content of the input will be replaced.
   This makes it trivial to implement controlled, validated inputs, without having to
   manage invalid data in your `app-db` or application component state.

   ### Introducing `on-error` and `show-error`

   If the validator returns false for a value, the input's `on-change` will not be called.
   Instead, the component's `on-error` prop will be called with an error message (which is generated by
   calling the `show-error` prop on the invalid value.)
     
   If the value later becomes valid, `on-error`
   will be called again with a value `nil`, to indicate there are no outstanding errors.
   
   Whether or not `on-error` is specified, invalid inputs are highlighted in red automatically.
                   
   ### Interactive example
   
   In this interactive example, text is only valid when it contains the character `q`:"
  (fn [data-atom]
    (let [data @data-atom
          on-change (fn [data] (reset! data-atom data))
          on-error (fn [err] (swap! data-atom assoc :error err))
          validator (fn [val] (string/includes? val "q"))
          show-error (fn [_] "Must include a 'q'")]
     [inputs/text (inputs/cursor data on-change [:value]
                                {:validator validator
                                 :show-error show-error
                                 :on-error on-error})]))
  {:value nil :error nil}
  {:inspect-data true})