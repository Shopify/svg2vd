[![License: MIT](https://img.shields.io/badge/License-MIT-yellow.svg)](https://opensource.org/licenses/MIT) ![GitHub Actions status](https://github.com/Shopify/svg2vd/workflows/Java%20CI/badge.svg)

# svg2vd
Convert SVGs to Android Vector Drawables from the command line.

## Building

Build using the bundled Gradle wrapper.

```bash
./gradlew jar
```


## Running

```bash
java -jar svg2vd-0.1.jar
```

### Help

```
Usage: svg2vd [OPTIONS] [SOURCE]... DEST

Options:
  -f, --force              Force overwrites any existing files in the OUTPUT
                           directory
  -v, --verbose            Verbose logging, show files as they are converted
  -c, --continue-on-error  If an error occurs, continue processing SVGs
  -o, --optimize           Run Avocado on generated VectorDrawables
  --version                Display information about svg2vd
  -h, --help               Show this message and exit

Arguments:
  SOURCE  SVG files
  DEST    Directory to save VectorDrawables
```

### Avocado support

To further optimize the VectorDrawable, use the `-o` option. This requires Avocado, a third-party app to be installed and accessible in your `PATH`.

Install `avocado` using `npm`

```bash
npm install -g avocado
```

See [Avocado's GitHub page](https://github.com/alexjlockwood/avocado) for more information.

# License

    MIT License
    
    Copyright (c) 2020 Shopify
    
    Permission is hereby granted, free of charge, to any person obtaining a copy
    of this software and associated documentation files (the "Software"), to deal
    in the Software without restriction, including without limitation the rights
    to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
    copies of the Software, and to permit persons to whom the Software is
    furnished to do so, subject to the following conditions:
    
    The above copyright notice and this permission notice shall be included in all
    copies or substantial portions of the Software.
    
    THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
    IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
    FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
    AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
    LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
    OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
    SOFTWARE.

