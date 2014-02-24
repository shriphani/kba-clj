(ns kba-clj.core
  (:require [clojure.java.io :as io]
            [thrift-clj.core :as thrift])
  (:use [byte-streams])
  (:import [java.io FileInputStream]
           [java.nio.charset Charset]
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

(defn stream-links
  [a-stream]
  (let [cset (Charset/forName "UTF-8")]
   (map
    (fn [an-item]
      (convert
       (.abs_url an-item)
       String))
    a-stream)))
