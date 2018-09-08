# parameter-validation
[![Build Status](https://travis-ci.org/rhwayfun/parameter-validation.svg?branch=master)](https://travis-ci.org/rhwayfun/parameter-validation)
[![Coverage Status](https://coveralls.io/repos/github/rhwayfun/parameter-validation/badge.svg?branch=master)](https://coveralls.io/github/rhwayfun/parameter-validation?branch=master)
[![Maven Central](https://maven-badges.herokuapp.com/maven-central/io.github.rhwayfun/spring-boot-rocketmq-starter/badge.svg)](http://search.maven.org/#artifactdetails%7Cio.github.rhwayfun%7Cparameter-validation%7C0.0.1-beta%7Cjar)
[![License](https://img.shields.io/badge/license-Apache%202.0-orange.svg)](http://www.apache.org/licenses/LICENSE-2.0)

A common parameter validation tool, make validation easier. Parameter verification is spread throughout our daily development. 
It is often necessary to write code for various parameter verifications. The code for these parameter verifications is basically repeated. 
As the version is iterated, the bad taste of the code begins to appear, violating the DRY principle. Therefore, 
we encapsulate the parameter check according to these pain points, and use it out of the box to perform basic parameter verification and 
custom verification rules.
                                                            
To avoid duplication and to be as simple as possible, the JSR 303 - Bean Validation specification provides the ability 
to support validation. Here we choose Hibernate Validator, which is probably the most well-known JSR implementation.