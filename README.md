## Java Server

Install Ruby dependencies. 

    jruby -S bundle install

To execute tests run:

    mvn test
    cd src/main && jruby -S rspec && cd ../..

To package and run:

    mvn package
    jruby runner.rb

Valid options include `-p <Port>` and `-d <Path>`.
