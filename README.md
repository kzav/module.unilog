# Duct module.unilog

Integrant multimethods for setting up a [unilog][] (SLF4J + Logback) logger for
the [Duct][] framework.

[unilog]: https://github.com/pyr/unilog
[duct]: https://github.com/duct-framework/duct

## Installation

To install, add the following to your project `:dependencies`:

    [ten-dimensions/module.unilog "0.1.0"]

## Usage

There are no configuration options for this. Just add the
`:duct.module/unilog` key to your configuration:

```clojure
{:duct.module/unilog {}}
```

When this configuration is initiated with `integrant.core/init`, the
`:duct.logger/timbre` key is replaced with an implementation of the
`duct.logger/Logger` protocol. See the [duct.logger][] library for how
to make use of this.

[duct.logger]: https://github.com/duct-framework/logger

## License

Copyright © 2023 Kazushiro ABO

This program and the accompanying materials are made available under the
terms of the Eclipse Public License 2.0 which is available at
http://www.eclipse.org/legal/epl-2.0.
