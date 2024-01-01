# Type Formatter

Tiny library to format text data to specific types.

## API

```
T parse(String text, Locale locale) - parses text to specific type

String format(T object, Locale locale) - formats text with default format

String format(T object,String formatExp, Locale locale) - formats text with provided format(formats are unique for each type)
```


## Supported types

These types are supported for parsing and formatting

- Date (DATE),
- Time (TIME),
- Currency (CURRENCY),
- Integer (INTEGER_NUMBER),
- Float (FLOAT_NUMBER),
- Email (EMAIL),
- SSN (SSN),
- Phone (PHONE),
- Bank Account (BANK_ACCOUNT)

## Usage

- Instantiate formatter factory
```
  var formatterInstance = FormatterFactory.getInstance();
```
- Retrieve a required formatter
```
  var formatter = formatterInstance.getFormatter(Type.EMAIL.name());
```

## Adding custom formatter

To add custom formatter do steps below

- Create a new formatter which implements formatter interface
```
 public class TestFormatter implements Formatter<String> { .... }
```
- Register formatter with type
```
formatterInstance.registerFormatter("test",formatter);
```
- Use it by retrieving by registered type
```
var formatter = formatterInstance.getFormatter("test");
```

## Build
```
mvnw clean install
```