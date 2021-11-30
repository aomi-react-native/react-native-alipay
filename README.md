# react-native-alipay

React Native 集成 AliPaySDK

## Installation

```sh
npm install @aomi/react-native-alipay
or
yarn add @aomi/react-native-alipay
```

## Usage

```js
import { AliPay } from '@aomi/react-native-alipay';

// ...
// 发起支付
const orderString = ''; // 服务端组装好的支付字符串
const result = await AliPay.pay(orderString, {
  appScheme: 'myapp',
  showLoading: false
})
```

## Contributing

See the [contributing guide](CONTRIBUTING.md) to learn how to contribute to the repository and the development workflow.

## License

MIT
