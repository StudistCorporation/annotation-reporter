# GitHub annotation test reporter for Kaocha

A Kaocha test reporter that outputs test failures in the format for use with GitHub annotations.

Use this reporter to have test failures show up automatically in GitHub pull requests as code annotations.

![image](https://github.com/user-attachments/assets/5b843a05-8d2d-42c6-b185-875436360ae2)

It assumes that tests are run in GitHub Actions.

## Installation

Available on Clojars.

```clj
[jp.studist/annotation-reporter "0.2.0"]
```
```clj
{jp.studist/annotation-reporter {:mvn/version "0.2.0}}
```

## Usage

Add the `--reporter` option to the Kaocha command you use to run tests.

```sh
# with lein for example
lein run -m kaocha.runner --reporter annotation.report/reporter
```
