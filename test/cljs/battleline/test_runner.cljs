(ns battleline.test-runner
  (:require
   [doo.runner :refer-macros [doo-tests]]
   [battleline.core-test]
   [battleline.common-test]))

(enable-console-print!)

(doo-tests 'battleline.core-test
           'battleline.common-test)
