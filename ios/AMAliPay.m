#import "AMAliPay.h"
#import <AlipaySDK/AlipaySDK.h>

@implementation AMAliPay

RCT_EXPORT_MODULE(AMAliPay)


#pragma 支付
RCT_EXPORT_METHOD(pay:(nonnull NSString *)orderString
                  withOptions: (NSDictionary *) options
                  withResolver:(RCTPromiseResolveBlock)resolve
                  withRejecter:(RCTPromiseRejectBlock)reject) {

    NSString *appScheme = options[@"appScheme"];
    // NOTE: 调用支付结果开始支付
   [[AlipaySDK defaultService]
    payOrder:orderString
    fromScheme:appScheme
    callback:^(NSDictionary *resultDic) {
       resolve(resultDic);
   }];
}

@end
