!function(){"use strict";function e(e){if(!e||"object"!=typeof e)return!1;var t=Object.getPrototypeOf(e);if(null===t)return!0;var o=t.constructor;return"function"==typeof o&&o instanceof o}var t,o,n;!function(e){e.open="open",e.closed="closed",e.leftOpenRightClosed="leftOpenRightClosed",e.leftClosedRightOpen="leftClosedRightOpen"}(t||(t={})),function(e){e.css="css",e.js="js",e.img="img",e.video="video",e.audio="audio"}(o||(o={})),function(e){e.desc="desc",e.asc="asc"}(n||(n={}));const r={},s=t=>e(t)?!0===t.__APP_AGENT_BASE64_FILE__?function(e){const t=atob(e.base64);let o=t.length;const n=new Uint8Array(o);for(;o--;)n[o]=t.charCodeAt(o);return new File([n],e.name,{type:e.type})}(t):JSON.stringify(t):t;chrome.runtime.onMessage.addListener((t,o,n)=>{
/*!
   * vtils v2.59.0
   * (c) Jay Fong <fjc0kb@gmail.com> (https://github.com/fjc0k)
   * Released under the MIT License.
   */
var a;return(a=async()=>{var a,i,c,d;switch(t.type){case"__APP_AGENT_BACKGROUND_HTTP_REQUEST__":const _=null===(a=o.tab)||void 0===a?void 0:a.id,l=new Headers(t.options.headers);if(e(t.options.body)&&!0===t.options.body.__APP_AGENT_FILE_BODY__){const{__APP_AGENT_FILE_BODY__:e,...o}=t.options.body,n=new FormData;for(const[e,t]of Object.entries(o))Array.isArray(t)?t.forEach(t=>n.append(e,s(t))):n.append(e,s(t));t.options.body=n,l.has("Content-Type")&&l.delete("Content-Type")}if(e(t.options.body)&&!0===t.options.body.__ARRAY_BUFFER_KEY__){const{data:e}=t.options.body;t.options.body=new Uint8Array(e).buffer}const u=new URL(t.options.url).origin;l&&(l.set("Referrer-Policy","no-referrer, strict-origin-when-cross-origin"),l.set("Referer",u),l.set("Origin",u),t.options.mode="cors",l.has("cookie")&&l.delete("cookie"));const p=new AbortController,{requestId:f=0,timeout:y=3e4}=null!==(i=t.runtimeOptions)&&void 0!==i?i:{},A=setTimeout(()=>p.abort(),y);r[f]={abortId:A,abortController:p};const b=await fetch(t.options.url,{...t.options,headers:l,redirect:"follow",signal:p.signal});clearTimeout(A);const T=new Headers(b.headers),h=T.get("x-app-agent-http-headers");if(null!=h){T.delete("x-app-agent-http-headers");for(const[e,t]of Object.entries(JSON.parse(h)))T.set(e,t)}const E=Object.fromEntries(T.entries()),g="file",P=T.get("content-type")||"application/octet-stream";let m=new Uint8Array;b.body?(_&&chrome.tabs.sendMessage(_,{type:"__APP_AGENT_HTTP_RESPONSE_START_LISTENER__",id:f,request:{url:t.options.url},response:{headers:E,status:b.status,statusText:b.statusText,url:b.url,ok:b.ok,redirected:b.redirected,type:b.type}}),await async function(e,t,o){const n=(e,t)=>{const o=new Uint8Array(e.length+t.length);return o.set(e,0),o.set(t,e.length),o};let r=new Uint8Array;const s=e.getReader();for(;;){const{done:e,value:a}=await s.read();if(e){o(r);break}r=n(r,a),t(a)}}(b.body,e=>{_&&chrome.tabs.sendMessage(_,{type:"__APP_AGENT_HTTP_RESPONSE_DATA_LISTENER__",id:f,data:[...e]})},e=>{m=e})):m=await b.arrayBuffer(),delete r[f];const R=function(e,t,o){let n="";const r=new Uint8Array(e),s=r.byteLength;for(let e=0;e<s;e++)n+=String.fromCharCode(r[e]);return{__APP_AGENT_BASE64_FILE__:!0,base64:btoa(n),name:t,type:o}}(m,g,P),{ok:w,status:O,statusText:N}=b;n({error:null,data:{ok:w,status:O,statusText:N,headers:E,base64File:R,arrayBuffer:new Uint8Array}});break;case"__APP_AGENT_HTTP_REQUEST_ABORT_LISTENER__":const{requestId:S=0}=null!==(c=t.runtimeOptions)&&void 0!==c?c:{},{abortId:C,abortController:v}=null!==(d=r[S])&&void 0!==d?d:{};C&&clearTimeout(C),v&&v.abort(),delete r[S]}},a()).catch(e=>{n({error:{message:String(e)}})}),!0}),chrome.action.onClicked.addListener((function(e){chrome.tabs.create({url:"https://app.apifox.com"})}))}();
