# MillGame

<div>

[![build](https://github.com/Billy-freespace/MillGame/actions/workflows/build.yml/badge.svg?branch=main)](https://github.com/Billy-freespace/MillGame/actions/workflows/build.yml)
[![pmd](https://github.com/Billy-freespace/MillGame/actions/workflows/pmd.yml/badge.svg?branch=test)](https://github.com/Billy-freespace/MillGame/actions/workflows/pmd.yml)
[![codecov](https://codecov.io/gh/Billy-freespace/MillGame/branch/main/graph/badge.svg?token=NJZOQUKC0T)](https://codecov.io/gh/Billy-freespace/MillGame)
[![Codacy Badge](https://app.codacy.com/project/badge/Grade/5043d99956d040769cba06312dff0cd0)](https://www.codacy.com/gh/Billy-freespace/MillGame/dashboard?utm_source=github.com&amp;utm_medium=referral&amp;utm_content=Billy-freespace/MillGame&amp;utm_campaign=Badge_Grade)
  

</div>


Mill Game - java

## Quickstart
Run game
* Minimal GUI version (include cli arguments to configure your game)
```bash
./gradlew minimal

# To get available game configuration options
./gradlew minimal --args="-h"
```
* Complete GUI version (include panels to configure your game)[STATE: in development]
```bash
./gradlew run
```

Build game and run unit tests (exclude **pmd** static analysis)
```bash
./gradlew build
```

Static Analysis (pmd tasks)
```bash
./gradlew check
```


## Documentation
Check our design diagrams on the [wiki](https://github.com/Billy-freespace/MillGame/wiki)
