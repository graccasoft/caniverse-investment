@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param boolean error
@param boolean reset
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sign In | Caniverse</title>
    <link rel="icon" href="/favicon.ico">
    <link href="/style.css" rel="stylesheet">

</head>

<body x-data="{ page: 'signin', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }">
@template.partials.preloader()

<div class="flex h-screen overflow-hidden">

    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">

        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">
                <div class="mb-6 flex flex-col gap-3 sm:flex-row sm:items-center sm:justify-between">
                    <h2 class="text-title-md2 font-bold text-black dark:text-white"></h2>
                    <img src="/images/logo/logo-icon.png" class="mx-auto login-logo">

                </div>

                <div class="bg-white border border-stroke dark:bg-boxdark dark:border-strokedark md:w-1/2 md:w-[50%] mx-auto rounded-sm shadow-default w-full">
                    <div class="flex flex-wrap items-center">
                        <div class="w-full p-4 sm:p-12.5 xl:p-17.5">
                            <span class="mb-1.5 block font-medium">Reset Password</span>
                            <h2 class="mb-9 text-2xl font-bold text-black dark:text-white sm:text-title-xl2">
                                Caniverse
                            </h2>

                            @if(reset)
                                <div class="mb-10 rounded-xl border border-success-500 bg-success-50 p-4 dark:border-success-500/30 dark:bg-success-500/15">
                                    <div class="flex items-start gap-3">
                                        <div class="-mt-0.5 text-success-500">
                                            <svg class="fill-current" width="24" height="24" viewBox="0 0 24 24"
                                                 fill="none" xmlns="http://www.w3.org/2000/svg">
                                                <path fill-rule="evenodd" clip-rule="evenodd"
                                                      d="M3.70186 12.0001C3.70186 7.41711 7.41711 3.70186 12.0001 3.70186C16.5831 3.70186 20.2984 7.41711 20.2984 12.0001C20.2984 16.5831 16.5831 20.2984 12.0001 20.2984C7.41711 20.2984 3.70186 16.5831 3.70186 12.0001ZM12.0001 1.90186C6.423 1.90186 1.90186 6.423 1.90186 12.0001C1.90186 17.5772 6.423 22.0984 12.0001 22.0984C17.5772 22.0984 22.0984 17.5772 22.0984 12.0001C22.0984 6.423 17.5772 1.90186 12.0001 1.90186ZM15.6197 10.7395C15.9712 10.388 15.9712 9.81819 15.6197 9.46672C15.2683 9.11525 14.6984 9.11525 14.347 9.46672L11.1894 12.6243L9.6533 11.0883C9.30183 10.7368 8.73198 10.7368 8.38051 11.0883C8.02904 11.4397 8.02904 12.0096 8.38051 12.3611L10.553 14.5335C10.7217 14.7023 10.9507 14.7971 11.1894 14.7971C11.428 14.7971 11.657 14.7023 11.8257 14.5335L15.6197 10.7395Z"
                                                      fill=""></path>
                                            </svg>
                                        </div>

                                        <div>
                                            <h4 class="mb-1 text-sm font-semibold text-gray-800 dark:text-white/90">
                                                Success
                                            </h4>

                                            <p class="text-sm text-gray-500 dark:text-gray-400">
                                                Password has been rest successfully. Please check your email for instructions
                                            </p>
                                        </div>
                                    </div>
                                </div>
                            @endif

                            <form method="post" action="/reset-password">
                                <input type="hidden" name="${csrfToken.getParameterName()}"
                                       value="${csrfToken.getToken()}">
                                <div class="mb-4">
                                    <label class="mb-2.5 block font-medium text-black dark:text-white">Your email address</label>
                                    <div class="relative">
                                        <input type="email" placeholder="Enter your email" name="email" required
                                               class="w-full rounded-lg border border-stroke bg-transparent py-4 pl-6 pr-10 outline-none focus:border-primary focus-visible:shadow-none dark:border-form-strokedark dark:bg-form-input dark:focus:border-primary">

                                        <span class="absolute right-4 top-4">
                            <svg class="fill-current" width="22" height="22" viewBox="0 0 22 22" fill="none"
                                 xmlns="http://www.w3.org/2000/svg">
                              <g opacity="0.5">
                                <path d="M19.2516 3.30005H2.75156C1.58281 3.30005 0.585938 4.26255 0.585938 5.46567V16.6032C0.585938 17.7719 1.54844 18.7688 2.75156 18.7688H19.2516C20.4203 18.7688 21.4172 17.8063 21.4172 16.6032V5.4313C21.4172 4.26255 20.4203 3.30005 19.2516 3.30005ZM19.2516 4.84692C19.2859 4.84692 19.3203 4.84692 19.3547 4.84692L11.0016 10.2094L2.64844 4.84692C2.68281 4.84692 2.71719 4.84692 2.75156 4.84692H19.2516ZM19.2516 17.1532H2.75156C2.40781 17.1532 2.13281 16.8782 2.13281 16.5344V6.35942L10.1766 11.5157C10.4172 11.6875 10.6922 11.7563 10.9672 11.7563C11.2422 11.7563 11.5172 11.6875 11.7578 11.5157L19.8016 6.35942V16.5688C19.8703 16.9125 19.5953 17.1532 19.2516 17.1532Z"
                                      fill=""></path>
                              </g>
                            </svg>
                          </span>
                                    </div>
                                </div>



                                @if(error)
                                    <div class="flex w-full border-l-6 border-[#F87171] bg-[#F87171] bg-opacity-[15%] px-7 py-8 shadow-md dark:bg-[#1B1B24] dark:bg-opacity-30 md:p-9">
                                        <div class="mr-5 flex h-9 w-full max-w-[36px] items-center justify-center rounded-lg bg-[#F87171]">
                                            <svg width="13" height="13" viewBox="0 0 13 13" fill="none"
                                                 xmlns="http://www.w3.org/2000/svg">
                                                <path d="M6.4917 7.65579L11.106 12.2645C11.2545 12.4128 11.4715 12.5 11.6738 12.5C11.8762 12.5 12.0931 12.4128 12.2416 12.2645C12.5621 11.9445 12.5623 11.4317 12.2423 11.1114C12.2422 11.1113 12.2422 11.1113 12.2422 11.1113C12.242 11.1111 12.2418 11.1109 12.2416 11.1107L7.64539 6.50351L12.2589 1.91221L12.2595 1.91158C12.5802 1.59132 12.5802 1.07805 12.2595 0.757793C11.9393 0.437994 11.4268 0.437869 11.1064 0.757418C11.1063 0.757543 11.1062 0.757668 11.106 0.757793L6.49234 5.34931L1.89459 0.740581L1.89396 0.739942C1.57364 0.420019 1.0608 0.420019 0.740487 0.739944C0.42005 1.05999 0.419837 1.57279 0.73985 1.89309L6.4917 7.65579ZM6.4917 7.65579L1.89459 12.2639L1.89395 12.2645C1.74546 12.4128 1.52854 12.5 1.32616 12.5C1.12377 12.5 0.906853 12.4128 0.758361 12.2645L1.1117 11.9108L0.758358 12.2645C0.437984 11.9445 0.437708 11.4319 0.757539 11.1116C0.757812 11.1113 0.758086 11.111 0.75836 11.1107L5.33864 6.50287L0.740487 1.89373L6.4917 7.65579Z"
                                                      fill="#ffffff" stroke="#ffffff"></path>
                                            </svg>
                                        </div>
                                        <div class="w-full">
                                            <h5 class="mb-3 font-bold text-[#B45454]">
                                                Email you provided could not be found
                                            </h5>
                                        </div>
                                    </div>
                                @endif


                                <div class="mb-5">
                                    <input type="submit" value="Rest password"
                                           class="w-full cursor-pointer rounded-lg border border-primary bg-primary p-4 font-medium text-white transition hover:bg-opacity-90">
                                </div>


                                <div class="mt-6 text-center">
                                    <p class="font-medium">
                                        Don’t have any account?
                                        <a href="/login" class="text-primary">Login</a>
                                    </p>
                                </div>
                            </form>
                        </div>
                    </div>
                </div>

            </div>
        </main>
    </div>
</div>
@template.partials.footer()

</body>
</html>