import { NativeModules, Platform } from 'react-native';

const LINKING_ERROR =
  `The package 'react-native-alipay' doesn't seem to be linked. Make sure: \n\n` +
  Platform.select({ ios: "- You have run 'pod install'\n", default: '' }) +
  '- You rebuilt the app after installing the package\n' +
  '- You are not using Expo managed workflow\n';

const AMAliPay = NativeModules.AMAliPay
  ? NativeModules.AMAliPay
  : new Proxy(
      {},
      {
        get() {
          throw new Error(LINKING_ERROR);
        },
      }
    );

export type PayOptions = {
  /**
   * 应用注册scheme,在AliSDKDemo-Info.plist定义URL types
   * Only ios
   */
  appScheme?: string;

  /**
   * 显示加载中 loading 框
   * Only Android
   */
  showLoading?: boolean;
};

export class AliPay {
  /**
   * 订单支付
   * @param orderString 服务端返回的订单字符串
   * @param options 参数选项
   *
   */
  static pay(orderString: string, options?: PayOptions) {
    const newOptions = {
      appScheme: 'app',
      showLoading: false,
      ...options,
    };

    return AMAliPay.pay(orderString, newOptions);
  }
}
