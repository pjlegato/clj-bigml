;; Copyright 2012 BigML
;; Licensed under the Apache License, Version 2.0
;; http://www.apache.org/licenses/LICENSE-2.0

(ns bigml.api.evaluation
  (:require (bigml.api [core :as api]))
  (:refer-clojure :exclude [list]))

(defn create
  "Creates an evaluation given a model and a dataset. Accepts the
   optional creation parameters defined in the BigML API docs:
      https://bigml.com/developers/evaluations#e_create"
  [model dataset & params]
  (let [params (apply api/query-params params)
        form-params (assoc (apply dissoc params api/conn-params)
                      :model (api/location model)
                      :dataset (api/location dataset))
        auth-params (select-keys params api/auth-params)]
    (api/create :evaluation
                (:dev_mode params)
                {:content-type :json
                 :form-params form-params
                 :query-params auth-params})))

(defn list
  "Retrieves a list of evaluations. Optional parameters are supported
   for pagination and filtering.  Details are available here:
      https://bigml.com/developers/evaluations#e_list"
  [& params]
  (apply api/list :evaluation params))