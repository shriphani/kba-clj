(ns kba-clj.core
  (:require [clojure.java.io :as io]
            [thrift-clj.core :as thrift])
  (:import [java.io FileInputStream]
           [org.apache.thrift.protocol TBinaryProtocol]
           [org.apache.thrift.transport
            TIOStreamTransport
            TTransport
            TTransportException]
           [streamcorpus StreamItem]))

(defn file-stream
  [a-file]
  (let [itransport (-> a-file
                       (FileInputStream.)
                       (TIOStreamTransport.))
        iprotocol  (new TBinaryProtocol itransport)]
    (do
      (.open itransport)
      (take-while
       identity
       (repeatedly
        (fn []
          (let [item (new StreamItem)]
            (try (do (.read item iprotocol)
                     item)
                 (catch TTransportException e (do (.close itransport)
                                                  nil))))))))))


