# baac

![Pull Request](https://github.com/tiagotrcz/baac/workflows/Pull%20Request/badge.svg)
[![codecov](https://codecov.io/gh/tiagotrcz/baac/branch/main/graph/badge.svg?token=8EM8TDX2AV)](https://codecov.io/gh/tiagotrcz/baac)

---

## Introduction

Simple app to show emojis, users avatar and google repos from GitHub public api.

---

## Setup Instructions

Clone the project and open it with the latest version of Android Studio.

---

## Architecture

The app has 3 modules: app, domain and data.
 - **app**: responsible for the user interface.
 - **domain**: it is a pure Kotlin module, responsible for holding the business logic.
 - **data**: responsible for get/send data from/to cache(local database) and remote (github api).
