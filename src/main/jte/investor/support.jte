@param org.springframework.security.web.csrf.CsrfToken csrfToken
@param io.caniverse.investment.model.entity.Investor investor
<!DOCTYPE html>
<html lang="en">
<head>
    @template.partials.header(title = "Support")
</head>
<body
        x-data="{ page: 'support', 'loaded': true, 'darkMode': true, 'stickyMenu': false, 'sidebarToggle': false, 'scrollTop': false }"
>
@template.partials.preloader()
<div class="flex h-screen overflow-hidden">
    @template.investor.aside()
    <div class="relative flex flex-1 flex-col overflow-y-auto overflow-x-hidden">
        @template.partials.profile(investor = investor)
        <main>
            <div class="mx-auto max-w-screen-2xl p-4 md:p-6 2xl:p-10">

                <h2 class="text-title-md2 font-bold text-black dark:text-white mb-4">Support Ticket</h2>
                <div class="rounded-sm border border-stroke bg-white shadow-default dark:border-strokedark dark:bg-boxdark">

                    <form action="/investor/support" method="post">
                        <input type="hidden" name="${csrfToken.getParameterName()}" value="${csrfToken.getToken()}">
                        <div class="p-6.5">

                            <div class="mb-4.5 flex flex-col gap-6 xl:flex-row">


                                <div class="w-full xl:w-1">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Subject
                                    </label>
                                    <input type="text" placeholder="Subject" name="subject" required
                                           class="w-full rounded border-[1.5px] border-stroke bg-transparent px-5 py-3 font-normal text-black outline-none transition focus:border-primary active:border-primary disabled:cursor-default disabled:bg-whiter dark:border-form-strokedark dark:bg-form-input dark:text-white dark:focus:border-primary">
                                </div>
                            </div>

                            <div class="mb-4.5 flex flex-col gap-6 xl:flex-row">
                                <div class="w-full xl:w-1/2">
                                    <label class="mb-3 block text-sm font-medium text-black dark:text-white">
                                        Message <span class="text-meta-1">*</span>
                                    </label>
                                    <textarea required name="description" placeholder="Enter a message..." type="text" rows="6" class="dark:bg-dark-900 w-full rounded-lg border border-gray-300 bg-transparent px-4 py-2.5 text-sm text-gray-800 shadow-theme-xs placeholder:text-gray-400 focus:border-brand-300 focus:outline-none focus:ring focus:ring-brand-500/10 dark:border-gray-700 dark:bg-gray-900 dark:text-white/90 dark:placeholder:text-white/30 dark:focus:border-brand-800" spellcheck="false"></textarea>
                                </div>

                            </div>

                            <button class="flex w-full justify-center rounded bg-primary p-3 font-medium text-gray hover:bg-opacity-90">
                                Submit Request
                            </button>
                        </div>
                    </form>

                </div>
            </div>
        </main>
    </div>
</div>
@template.partials.footer()
</body>

</html>