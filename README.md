## [WIP] React Native bindings for Omise


### Currently works only in android

Quick Usage:

```js
import Omise from 'react-native-omise';

Omise.getToken(apiKey, {
      number: '4242424242424242',
      name: 'rahul gaba',
      expirationMonth: '10',
      expirationYear: '2020',
      securityCode: '123'
    })
    .then(console.log)
    .catch(console.log);
```

Documentation for android and iOS support is coming soon...
